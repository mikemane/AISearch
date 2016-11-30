package heuristics;

import board.EightPuzzleBoard;
import datastructures.State;

/**
 * Created by udokanwosu on 12/11/2016.
 */
public class UniformSearchHeuristics implements Heuristics {


    @Override
    public double getHeuristic(State state, State goalState) {
        return 1;
    }
}
