package board;

import datastructures.Action;
import datastructures.Coordinates;

/**
 * Created by un4 on 05/11/16.
 */
public class EightPuzzleBoard implements Cloneable {


    private final int BOARD_SIZE = 3;
    private final int EMPTY_POSITION = 0;
    private int[] state;

    public EightPuzzleBoard(int[] state) {
        this.state = state;
    }

    public EightPuzzleBoard() {
        this.state = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0};
    }

    public int[] getState() {
        return state;
    }


    public Coordinates getCoordinates(int value) {
        int absolutePosition = getPositionOf(value);
        return new Coordinates(getXPosition(absolutePosition), getYPosition(absolutePosition));
    }

    public Integer getItemAtCoodinate(Coordinates coordinates) {
        return getValueAtPosition(coordinates.getX(), coordinates.getY());
    }

    public void move(Action action) {
        //Moves depending on action;
        switch (action) {
            case UP:
                moveGapUp();
                break;
            case DOWN:
                moveGapDown();
                break;
            case LEFT:
                moveGapLeft();
                break;
            case RIGHT:
                moveGapRight();
                break;
        }
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


    private void updateValue(int xPosition, int yPosition, int value) {
        this.state[getAbsolutePosition(xPosition, yPosition)] = value;
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

    private Integer getValueAtPosition(int x, int y) {
        int absolutePositon = getAbsolutePosition(x, y);
        return getState()[absolutePositon];
    }


    /**
     * Gets the index position.
     *
     * @return the index of the empty position.
     */
    public int getEmptyPositon() {
        return this.getPositionOf(EMPTY_POSITION);
    }

    public int getPositionOf(int value) {
        int result = -1;
        for (int i = 0; i < this.state.length; i++) {
            if (this.state[i] == value)
                result = i;
        }
        return result;
    }

    /**
     * Checks if this move is legal.
     *
     * @param direction the direction to move towards.
     * @return true if this move is legal.
     */
    public boolean canMove(Action direction) {
        boolean result = true;
        int absPos = getPositionOf(EMPTY_POSITION);
        switch (direction) {
            case UP:
                result = (getYPosition(absPos) != 0);
                break;
            case DOWN:
                result = (getYPosition(absPos) != 2);
                break;
            case LEFT:
                result = (getYPosition(absPos) != 0);
                break;
            case RIGHT:
                result = (getXPosition(absPos) != 2);
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int counter = 0;
        for (int i : getState()) {
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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        EightPuzzleBoard boardClone = (EightPuzzleBoard) super.clone();
        return boardClone;
    }
}
