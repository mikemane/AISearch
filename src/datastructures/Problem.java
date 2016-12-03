package datastructures;

import costfunctions.CostFunction;

/**
 * Created by mikemane on 04/11/2016.
 */
public class Problem {
    private GoalTest goalTest;
    private Integer pathCost;
    private State initialState;
    private CostFunction pathCostFunction;

    public Problem(GoalTest goalTest, State initialState, CostFunction pathCostFunction) {
        this.initialState = initialState;
        this.pathCostFunction = pathCostFunction;
        this.goalTest = goalTest;
    }

    public Integer getPathCost() {
        return pathCost;
    }

    public void setPathCost(Integer pathCost) {
        this.pathCost = pathCost;
    }

    public void setInitialState(State initialState) {
        this.initialState = initialState;
    }

    public State goalState() {
        return this.goalTest.getGoalState();
    }

    public CostFunction getPathCostFunction() {
        return pathCostFunction;
    }

    public void setPathCostFunction(CostFunction pathCostFunction) {
        this.pathCostFunction = pathCostFunction;
    }

    public State getInitialState() {
        return initialState;
    }

}
