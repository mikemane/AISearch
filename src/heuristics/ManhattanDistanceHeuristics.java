package heuristics;

import board.EightPuzzleBoard;
import datastructures.Coordinates;
import datastructures.State;

/**
 * Created by udokanwosu on 12/11/2016.
 */
public class ManhattanDistanceHeuristics implements Heuristics {

    @Override
    public double getHeuristic(State state, State goalState) {
        return this.getManhattenDistance(state, goalState);
    }

    private double getManhattenDistance(State state, State goalState) {
        double cost = 0;
        for (int i = 0; i < goalState.getCurrentState().length; i++) {
            int currentValue =  state.getCurrentState()[i];
            Coordinates goalItem = goalState.getCoordinates(currentValue);
            Coordinates currentItem = state.getCoordinates(currentValue);
            int newCost =  Math.abs(goalItem.getX() - currentItem.getX()) + Math.abs(goalItem.getY() - currentItem.getY());
            cost += newCost;
        }
        return cost;
    }
}
