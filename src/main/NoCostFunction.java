package main;

import costfunctions.CostFunction;
import datastructures.State;

/**
 * Created by un4 on 02/12/16.
 */
public class NoCostFunction implements CostFunction {
    @Override
    public double calculateCost(State state, State goalState) {
        return 0;
    }
}
