package heuristicFunction;

import datastructures.State;

/**
 * Created by un4 on 02/12/16.
 */
public class UniformHeuristicFunction extends HeuristicFunction {

    public UniformHeuristicFunction() {
        super(null);
    }

    @Override
    public double calculateCost(State state, State goalState) {
        return 1;
    }
}
