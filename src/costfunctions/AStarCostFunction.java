package costfunctions;

import datastructures.Action;
import datastructures.State;
import heuristics.Heuristics;

/**
 * Created by un4 on 30/11/16.
 */
public class AStarCostFunction implements CostFunction {


    private Heuristics heuristics;

    public AStarCostFunction(Heuristics heuristics) {
        this.heuristics = heuristics;
    }

    @Override
    public double calculateCost(State state, Action action, State successorState) {
        return this.heuristics.getHeuristic(state, successorState);
    }
}
