package datastructures;

import board.EightPuzzleBoard;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static board.EightPuzzleBoard.EMPTY_POSITION;

/**
 * Created by un4 on 29/11/16.
 */
public class State {

    private int[] currentState;
    public static int SIZE = 3;

    /**
     * this is the current state.
     * @param currentState this is the current state.
     */
    public State(int[] currentState) {
        this.currentState = new int[currentState.length];
        System.arraycopy(currentState, 0, this.currentState, 0, currentState.length);
//        this.currentState = currentState;
    }

    public int[] getCurrentState() {
        return currentState;
    }

    /**
     *
     * @param currentState
     */
    public void setCurrentState(int[] currentState) {
        this.currentState = currentState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Arrays.equals(currentState, state.currentState);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(currentState);
    }

    public int getPositionOf(int value) {
        int result = -1;
        for (int i = 0; i < this.currentState.length; i++) {
            if (this.currentState[i] == value)
                result = i;
        }
        return result;
    }

    /**
     * this checks if a move is legal.
     * @param direction this is the direction  to move to.
     * @return this retuen if this move is legal.
     */
    public boolean canMove(Action direction) {
        boolean result = true;
        int absPos = getPositionOf(EMPTY_POSITION);
        switch (direction) {
            case UP:
                result = (getXPosition(absPos) != 0);
                break;
            case DOWN:
//                result = (getXPosition(absPos) != SIZE - 1);
                result = !(getXPosition(absPos) >= SIZE - 1);
                break;
            case LEFT:
                result = (getYPosition(absPos) != 0);
                break;
            case RIGHT:
//                result = (getYPosition(absPos) != SIZE - 1);
                result = !(getYPosition(absPos) >= SIZE - 1);
                break;
        }
        return result;
    }


    /**
     * this gets the y position.
     * @param position this the positon.
     * @return
     */
    private int getYPosition(int position) {
        return position % SIZE;
    }

    /**
     * this gets the x position.
     * @param position
     * @return
     */
    private int getXPosition(int position) {
        return position / SIZE;
    }

    /**
     * Gets the absolute positon.
     * @param x
     * @param y
     * @return
     */
    private int getAbsolutePosition(int x, int y) {
//        System.out.println(x + " " + y);
        return SIZE * x  + y;
    }

    /**
     * Non mutable state space.
     *
     * @param action action to perform to the state space.
     * @return the new state with the moved value.
     */
    public State move(Action action) {
        State state = new State(this.currentState);
        switch (action) {
            case UP:
                state.moveGapUp();
                break;
            case DOWN:
                state.moveGapDown();
                break;
            case LEFT:
                state.moveGapLeft();
                break;
            case RIGHT:
                state.moveGapRight();
                break;
        }
        return state;
    }

    public void moveGapUp() {
        int gapPos = this.getEmptyPositon();
        int x = getXPosition(gapPos);
        int y = getYPosition(gapPos);
        if (!(x == 0)) {
            int valueOnTop = getValueAtPosition(x - 1, y);
            updateValue(x, y, valueOnTop);
            updateValue(x - 1, y, 0);
        }
    }

    public Coordinates getCoordinates(int value) {
        int absolutePosition = getPositionOf(value);
        return new Coordinates(getXPosition(absolutePosition), getYPosition(absolutePosition));
    }

    public int getEmptyPositon() {
        return this.getPositionOf(EMPTY_POSITION);
    }

    private void updateValue(int xPosition, int yPosition, int value) {
        int absPosition = getAbsolutePosition(xPosition,yPosition);
        this.currentState[absPosition] = value;
    }

    private Integer getValueAtPosition(int x, int y) {
        int absolutePositon = getAbsolutePosition(x, y);
//        System.out.println(x + " " + y);
        return getCurrentState()[absolutePositon];
    }


    /**
     * this moves tiles right.
     */
    public void moveGapRight() {
        int gapPos = getEmptyPositon();
        int x = getXPosition(gapPos);
        int ypos = getYPosition(gapPos);
        if (!(ypos == SIZE - 1)) {
            int valueOnRight = getValueAtPosition(x, ypos + 1);
            updateValue(x, ypos, valueOnRight);
            updateValue(x, ypos + 1, 0);
        }
    }

    public void moveGapLeft() {
        int gapPos = getEmptyPositon();
        int x = getXPosition(gapPos);
        int ypos = getYPosition(gapPos);
        if (!(ypos == 0)) {
            int valueOnLeft = getValueAtPosition(x, ypos - 1);
            updateValue(x, ypos, valueOnLeft);
            updateValue(x, ypos - 1, 0);
        }

    }

    public void moveGapDown() {
        int gapPos = getEmptyPositon();
        int x = getXPosition(gapPos);
        int y = getYPosition(gapPos);
        if (!(x == SIZE - 1)) {
            int valueOnBottom = getValueAtPosition(x + 1, y);
            updateValue(x, y, valueOnBottom);
            updateValue(x + 1, y, 0);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(this.currentState);
    }

    public String showMeStuff() {
        StringBuilder builder = new StringBuilder();
        int counter = 0;
        for (int i : getCurrentState()) {
            if (counter % SIZE == 0 && counter != 0)
                if (counter % SIZE == 0 && counter != 0)
                    builder.append(System.getProperty("line.separator"))
                            .append("| ")
                            .append(i)
                            .append(" |");
                else
                    builder.append("| ")
                            .append(i)
                            .append(" |");
            counter++;
        }
        return builder.toString();
    }

    /**
     * this gets the rows of a value
     * @param value
     * @return
     */
    public int[] getRows(int value) {
        int[] rows = new int[SIZE];
        Coordinates coord = getCoordinates(value);
        for (int i = 0; i < SIZE; i++) {
            rows[i] = getValueAtPosition(coord.getX(), i);
        }
        return rows;
    }

    public int[] getColumns(int value) {
        int[] cols = new int[SIZE];
        Coordinates coord = getCoordinates(value);
        for (int i = 0; i < SIZE; i++) {
            cols[i] = getValueAtPosition(i, coord.getY());
        }
        return cols;
    }

}
