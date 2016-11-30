package heuristics;

import board.EightPuzzleBoard;
import datastructures.State;

/**
 * Created by udokanwosu on 12/11/2016.
 */
public interface Heuristics {
    double getHeuristic(State state , State goalState);
}
