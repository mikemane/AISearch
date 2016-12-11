package heuristics;

import datastructures.Coordinates;
import datastructures.State;

/**
 * Created by un4 on 10/12/16.
 */
public class LinearConflictHeuristics implements Heuristics {
    @Override
    public double getHeuristic(State state, State goalState) {
        return 0;
    }

    private double linearConflictHeuristics(State state, State goalState) {
        double cost = 0;
        double conflict = 0;
        int[] rows = new int[(int) Math.sqrt(state.getCurrentState().length + 1)];
        int[] columns = new int[(int) Math.sqrt(state.getCurrentState().length + 1)];

        for (int i = 0; i < goalState.getCurrentState().length; i++) {
            int currentValue = state.getCurrentState()[i];
            Coordinates goalItem = goalState.getCoordinates(currentValue);
            Coordinates currentItem = state.getCoordinates(currentValue);
            int newCost = Math.abs(goalItem.getX() - currentItem.getX()) + Math.abs(goalItem.getY() - currentItem.getY());
//            if(newCost != 0){
//                newCost += calculateConflicts(currentValue , goalState.getColumns(currentValue));
//            }
//            int [] rows = state
            cost += newCost;
        }
        return cost;
    }


    double calculateConflicts(int[] goalArray) {

        for (int i : goalArray) {
        }
//        for (int i : goalArray) {
////            if(i == a)
//        }
        return 0;
    }
}
