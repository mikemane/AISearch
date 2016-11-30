package datastructures;

/**
 * Created by un4 on 05/11/16.
 */
public class GoalTest {

    private State goalState;
    public GoalTest(State goalState){
        this.goalState = goalState;
    }

    public State getGoalState() {
        return goalState;
    }

    public void setGoalState(State goalState) {
        this.goalState = goalState;
    }
}
