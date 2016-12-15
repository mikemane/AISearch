package heuristicFunction;

import costfunctions.CostFunction;
import datastructures.State;
import heuristics.Heuristics;

/**
 * Created by un4 on 02/12/16.
 */
public class HeuristicFunction implements CostFunction {
    private Heuristics heuristics;

    /**
     * This is the heuristic function.
     * @param heuristics
     */
    public HeuristicFunction(Heuristics heuristics) {
        this.heuristics = heuristics;
    }

    /**
     * This performs calculation of cost unction from one to the other.
     * @param state this is the current state.
     * @param goalState this is the foal state.
     * @return this returns the cost taken from one location to the other.
     */
    @Override
    public double calculateCost(State state, State goalState) {
        return this.heuristics.getHeuristic(state, goalState);
    }
}
