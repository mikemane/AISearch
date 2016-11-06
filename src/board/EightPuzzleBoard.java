package board;

import datastructures.Action;
import datastructures.Coordinates;

/**
 * Created by un4 on 05/11/16.
 */
public class EightPuzzleBoard {


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
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
        }
    }

    private void moveUp() {

    }

    private void moveLeft() {

    }

    private void moveRight() {

    }

    private void moveDown() {

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
        for (int i : this.state) {
            if (i == value)
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
    private boolean canMoveInDirection(Action direction) {
//        if()
        return false;
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
}
