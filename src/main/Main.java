package main;

import board.EightPuzzleBoard;
import datastructures.Problem;

/**
 * Created by mikemane on 04/11/2016.
 */
public class Main {


    public static void main(String[] args) {

        int[] puzzle = new int[]{1, 3, 2, 4, 5, 6, 7, 8, 0};
        EightPuzzleBoard puzzleBoard = new EightPuzzleBoard(puzzle);
        System.out.println(puzzleBoard.toString());
        System.out.println(puzzleBoard.getCoordinates(0).toString());

    }

}
