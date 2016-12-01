package test;

import datastructures.State;
import heuristics.Heuristics;
import heuristics.TilesMisplacedHeuristics;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by un4 on 30/11/16.
 */
public class TilesMisplacedHeuristicsTest {

    /**
     * This tests the misplaced tiles value.
     * @throws Exception
     */
    @Test
    public void getHeuristic() throws Exception {
        int[] values = new int[]{1, 3, 2, 4, 5, 6, 7, 0, 8};
        int[] goalState = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0};
        State state = new State(values);
        State goal = new State(goalState);

        Heuristics tl = new TilesMisplacedHeuristics();
        int mis = (int) tl.getHeuristic(state, goal);
        assertEquals(4, mis);
    }

}