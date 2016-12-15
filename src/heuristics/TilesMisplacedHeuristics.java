package heuristics;

import board.EightPuzzleBoard;
import datastructures.State;

/**
 * Created by un4 on 30/11/16.
 */
public class TilesMisplacedHeuristics implements Heuristics {


    @Override
    public double getHeuristic(State state, State goalState) {
        return misplacedTiles(state, goalState);
    }

    /**
     * This returns the tiles out of place fromt he goal state and the current state.
     * @param state
     * @param goalState
     * @return
     */
    public double misplacedTiles(State state, State goalState) {
        double counter = 0;

        for (int i = 0; i < goalState.getCurrentState().length; i++) {
            if (state.getCurrentState()[i] != goalState.getCurrentState()[i])
                counter++;
        }

        return counter;
    }
}
