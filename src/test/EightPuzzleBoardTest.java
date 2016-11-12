package test;

import board.EightPuzzleBoard;
import datastructures.Coordinates;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by un4 on 05/11/16.
 */
public class EightPuzzleBoardTest {


    EightPuzzleBoard board;
    int [] puzzle;

    @Before
    public void initBefore() {
        // 1 2 3
        // 4 5 6
        // 7 8 0
        puzzle = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0};
        board = new EightPuzzleBoard(puzzle);
    }

    @Test
    public void getValueAtPosition() throws Exception {

        Coordinates coordinates = new Coordinates(0, 0);
        assertEquals(board.getCoordinates(1), coordinates);

        Coordinates coordinates2 = new Coordinates(2, 0);
        assertEquals(board.getCoordinates(3), coordinates);

//        Coordinates coordinate3 = new Coordinates(2, 2);
//        assertEquals(board.getCoordinates(), coordinates);
    }

    @Test
    public void move() throws Exception {

    }

    @Test
    public void getItemAtCoordinate() {
        Coordinates coordinates = new Coordinates(2, 2);
        Integer i = 0;
        assertEquals(i, board.getItemAtCoodinate(coordinates));
        coordinates = new Coordinates(1, 2);
        i = 6;
        assertEquals(i, board.getItemAtCoodinate(coordinates));
        coordinates = new Coordinates(1, 2);
        i = 6;
        assertEquals(i, board.getItemAtCoodinate(coordinates));
    }

    @Test
    public void getCoordinates() throws Exception {
        assertEquals(board.getCoordinates(1), new Coordinates(0, 0));
        assertEquals(board.getCoordinates(0), new Coordinates(2, 2));
//        assertEquals(board.getCoordinates(6) , new Coordinates(2,1));
    }

    @Test
    public void getEmptyPositon() throws Exception {
        int position = board.getEmptyPositon();
        assertEquals(8, position);
    }

    @Test
    public void getPositionOf() throws Exception {
        assertEquals(8, board.getPositionOf(0));
        assertEquals(7, board.getPositionOf(8));
    }

    @Test
    public void getState() throws Exception {
        assertEquals(this.puzzle , board.getState());
    }


    @Test
    public void getItemAtCoodinate() throws Exception {
        Coordinates coordinates = new Coordinates(2, 2);
        Coordinates coordinates2 = new Coordinates(0, 0);
        assertEquals(coordinates, board.getCoordinates(0));
        assertEquals(coordinates2, board.getCoordinates(1));
    }


    @Test
    public void moveGapUp() throws Exception {
        assertEquals(8, board.getEmptyPositon());
        board.moveGapUp();
        assertEquals(5, board.getEmptyPositon());

    }

    @Test
    public void moveGapRight() throws Exception {

    }

    @Test
    public void moveGapLeft() throws Exception {
        board.moveGapLeft();
        assertEquals(7, board.getEmptyPositon());
        assertEquals(8, board.getPositionOf(8));
        assertEquals(new Coordinates(2, 2), board.getCoordinates(8));
    }

    @Test
    public void moveGapDown() throws Exception {
        board.moveGapLeft();
        assertEquals(7, board.getEmptyPositon());
        assertEquals(new Coordinates(2, 2), board.getCoordinates(8));
    }

    @Test
    public void getEmptyPositon1() throws Exception {

    }
}