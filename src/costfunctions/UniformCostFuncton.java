package costfunctions;

import datastructures.Action;
import datastructures.State;

/**
 * Created by un4 on 29/11/16.
 */
public class UniformCostFuncton implements CostFunction {

    private int cost;

    public UniformCostFuncton() {
        this.cost = 1;
    }

    @Override
    public double calculateCost(State state, State successorState) {
        return this.cost;
    }
}
