package heuristics;

import board.EightPuzzleBoard;
import datastructures.State;

/**
 * Created by udokanwosu on 12/11/2016.
 */
public class UniformSearchHeuristics implements Heuristics {

    /**
     * Assigns a heuristic of 1
     * @param state
     * @param goalState
     * @return
     */
    @Override
    public double getHeuristic(State state, State goalState) {
        return 1;
    }
}
