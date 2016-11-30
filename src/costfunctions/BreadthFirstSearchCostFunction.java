package costfunctions;

import datastructures.Action;
import datastructures.State;

/**
 * Created by un4 on 29/11/16.
 * */
public class BreadthFirstSearchCostFunction implements CostFunction {

    private int cost;

    public BreadthFirstSearchCostFunction() {
        this.cost = 1;
    }

    @Override
    public double calculateCost(State state, Action action, State successorState) {
        return this.cost;
    }
}
