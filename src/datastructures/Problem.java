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

    /**
     * This identifies the problem takes.
     * @param goalTest goal test to  test for.
     * @param initialState this is the initial state.
     * @param pathCostFunction this is the path cost function.
     */
    public Problem(GoalTest goalTest, State initialState, CostFunction pathCostFunction) {
//        this.initialState = initialState;
//        this.pathCostFunction = pathCostFunction;
//        this.goalTest = goalTest;
        setInitialState(initialState);
        this.goalTest = goalTest;
        this.setPathCostFunction(pathCostFunction);
    }

    public Integer getPathCost() {
        return pathCost;
    }

    public void setPathCost(Integer pathCost) {
        this.pathCost = pathCost;
    }

    public void setInitialState(State initialState) {
        this.initialState = initialState;
        State.SIZE = (int) Math.sqrt(initialState.getCurrentState().length);
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

    public void setGoalState(State state) {
        this.goalTest.setGoalState(state);
    }

    public State getGoalState() {
        return this.goalTest.getGoalState();
    }
}