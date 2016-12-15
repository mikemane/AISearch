package heuristics;

import board.EightPuzzleBoard;
import datastructures.State;

/**
 * Created by udokanwosu on 12/11/2016.
 */
public interface Heuristics {

    /**
     * Interface that performa a heuristic evaluation from a point to another.
     * @param state this is the current state of the game.
     * @param goalState this is the goal state of the game.
     * @return this returns the estimated heuristic value.
     */
    double getHeuristic(State state , State goalState);
}
