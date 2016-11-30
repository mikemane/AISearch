package test;

import datastructures.Node;

/**
 * Created by mikemane on 04/11/2016.
 */
public class EightPuzzleGoalTest {

    // 1 2 3
    // 4 5 6
    // 7 8 0
    private final int[] STATE = {1, 2, 3, 4, 5, 6, 7, 8, 0};

    public boolean isAtGoal(Object nodeState) {
        Node node = (Node) nodeState;
        return node.getState().equals(STATE);
    }

}
