package heuristics;

import board.EightPuzzleBoard;

/**
 * Created by udokanwosu on 12/11/2016.
 */
public class UniformSearchHeuristics implements Heuristics {
    @Override
    public double getHeuristic(EightPuzzleBoard board) {
        return 1;
    }
}
