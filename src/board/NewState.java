package board;

import datastructures.Action;
import datastructures.Coordinates;

/**
 * Created by un4 on 29/11/16.
 */
public class NewState {

    private int[][] state;
    private int width, height;

    public NewState(int[][] state, int width) {
//        this.state = state;

        this.state = new int[state.length][state.length];
        System.arraycopy(state, 0, this.state, 0, state.length);

        this.width = width;
        this.height = width;
    }

    void createArray(int[][] old) {
        int[][] current = new int[old.length][old.length];
        for (int i = 0; i < old.length; i++)
            for (int j = 0; j < old[i].length; j++)
                old[i][j] = current[i][j];
    }

    public int[][] getState() {
        return state;
    }

    public int getWidth() {
        return width;
    }

    public NewState move(Action direction) {
        NewState newState = new NewState(this.getState(), this.getWidth());
        switch (direction) {
            case DOWN:
                moveUp();
            case UP:
                moveDown();
            case LEFT:
                moveLeft();
            case RIGHT:
                moveRight();
        }
        return newState;
    }

    private void moveUp() {

    }

    private void moveDown() {
    }

    private void moveLeft() {
    }

    private void moveRight() {
    }

    private Coordinates getGapPosition() {
        for (int i = 0; i < this.state.length; i++)
            for (int j = 0; j < this.state.length; j++)
                if (this.state[i][j] == 0)
                    return new Coordinates(i, j);

        return null;
    }

    private Coordinates getPositionOf(int value) {
        for (int i = 0; i < this.state.length; i++)
            for (int j = 0; j < this.state.length; j++)
                if (this.state[i][j] == value)
                    return new Coordinates(i, j);
        return null;
    }

    public boolean canMove(Action direction) {
        return false;
    }
}
