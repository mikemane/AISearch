package heuristics;

import datastructures.State;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by un4 on 30/11/16.
 */
public class ManhattanDistanceHeuristicsTest {

    @Test
    public void getHeuristics() throws Exception {

        int[] first = new int[]{1, 2, 3, 4, 8, 7, 5, 6, 0};
        int[] second = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0};
        int[] third = new int[]{8, 7, 6, 5, 4, 3, 2, 1, 0};

        Heuristics heuristics = new ManhattanDistanceHeuristics();
        int distance = (int) heuristics.getHeuristic(new State(first), new State(second));
        int d3 = (int) heuristics.getHeuristic(new State(first) , new State(third));
        assertEquals(8, distance);
        assertEquals(16, d3);

    }

}