package HeuristicFunction;

import costfunctions.CostFunction;
import datastructures.State;
import heuristics.Heuristics;

/**
 * Created by un4 on 02/12/16.
 */
public class HeuristicFunction implements CostFunction {
    private Heuristics heuristics;

    public HeuristicFunction(Heuristics heuristics) {
        this.heuristics = heuristics;
    }

    @Override
    public double calculateCost(State state, State goalState) {
        return this.heuristics.getHeuristic(state, goalState);
    }
}
