package datastructures;

import board.EightPuzzleBoard;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static board.EightPuzzleBoard.BOARD_SIZE;
import static board.EightPuzzleBoard.EMPTY_POSITION;

/**
 * Created by un4 on 29/11/16.
 */
public class State {

    private int[] currentState;
    private int size = 3;

    public State(int[] currentState) {
        this.currentState = new int[currentState.length];
        System.arraycopy(currentState, 0, this.currentState, 0, currentState.length);
//        this.currentState = currentState;
    }

    public int[] getCurrentState() {
        return currentState;
    }

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

    public boolean canMove(Action direction) {
        boolean result = true;
        int absPos = getPositionOf(EMPTY_POSITION);
        switch (direction) {
            case UP:
                result = (getXPosition(absPos) != 0);
                break;
            case DOWN:
                result = (getXPosition(absPos) != 2);
                break;
            case LEFT:
                result = (getYPosition(absPos) != 0);
                break;
            case RIGHT:
                result = (getYPosition(absPos) != 2);
                break;
        }
        return result;
    }


    private int getYPosition(int position) {
        return position % BOARD_SIZE;
    }

    private int getXPosition(int position) {
        return position / BOARD_SIZE;
    }

    private int getAbsolutePosition(int x, int y) {
        return x * BOARD_SIZE + y;
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
        this.currentState[getAbsolutePosition(xPosition, yPosition)] = value;
    }

    private Integer getValueAtPosition(int x, int y) {
        int absolutePositon = getAbsolutePosition(x, y);
        return getCurrentState()[absolutePositon];
    }


    public void moveGapRight() {
        int gapPos = getEmptyPositon();
        int x = getXPosition(gapPos);
        int ypos = getYPosition(gapPos);
        if (!(ypos == 2)) {
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
        if (!(x == 2)) {
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
            if (counter % 3 == 0 && counter != 0)
                builder.append("\n")
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

    public int[] getRows(int value){
        int[] rows = new int[size];
        Coordinates coord = getCoordinates(value);
        for(int i = 0 ; i < this.size; i++){
            rows[i] = getValueAtPosition(coord.getX() , i);
        }
        return rows;
    }

    public int[] getColumns(int value){
        int [] cols = new int[size];
        Coordinates coord = getCoordinates(value);
        for(int i = 0 ; i < this.size; i++){
            cols[i] = getValueAtPosition(i , coord.getY());
        }
        return cols;
    }

//    public int getAccurateRow(int value){
//        return po
//    }

}
