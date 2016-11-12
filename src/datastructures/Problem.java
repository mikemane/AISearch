package datastructures;

import java.util.List;

/**
 * Created by mikemane on 04/11/2016.
 */
public class Problem {
    private List<Action> actionsTaken;
    private GoalTest goalTestCheck;
    private Integer pathCost;
    private int[] initialState;

    public Problem(GoalTest goalTest, int[] initialState) {
        this.initialState = initialState;
    }

    public Integer getPathCost() {
        return pathCost;
    }

    public void setPathCost(Integer pathCost) {
        this.pathCost = pathCost;
    }

    public int[] getInitialState() {
        return initialState;
    }

    public void setInitialState(int[] initialState) {
        this.initialState = initialState;
    }

    public void setActionsTaken(List<Action> actionsTaken) {
        this.actionsTaken = actionsTaken;
    }

    public List<Action> getActionsTaken() {
        return actionsTaken;
    }
}
