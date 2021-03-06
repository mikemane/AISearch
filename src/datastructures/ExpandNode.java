package datastructures;

import board.EightPuzzleBoard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by udokanwosu on 12/11/2016.
 */
public class ExpandNode {
    private Problem problem;
    private int numberOfExpansions = 0;

    /**
     * This takes problem.
     * @param problem this is the problem that needs to be solved.
     */
    public ExpandNode(Problem problem) {
        this.problem = problem;
    }


    /**
     * Creates a node with the relevant values.
     *
     * @param parent this is the parent node.
     * @param state  this is the current state of the board.
     * @return the created node.
     */
    private Node createNode(Node parent, Action action, double cost, State state) {
        return new Node(parent, action, cost, state);
    }

    /**
     * Expands the node gets the successor states.
     *
     * @param parentNode The parent node to get the successor states.
     * @return the list of the children states.
     */
    public List<Node> expandNode(Node parentNode) {
        List<Node> successors = new ArrayList<>();
//        for (Action direction : Action.values()) {
//            if (parentNode.getState().canMove(direction)) {
//                State newState = parentNode.getState().move(direction);
////                double cost = this.problem.getPathCostFunction().calculateCost(parentNode.getState(), newState);
//                Node newNode = createNode(parentNode, parentNode.getgCost() + 1, newState);
//                successors.add(newNode);
//            }
//        }
        Arrays.stream(Action.values()).forEach(action -> {
            if (parentNode.getState().canMove(action)) {
                State newState = parentNode.getState().move(action);
                double cost = this.problem.getPathCostFunction().calculateCost(parentNode.getState(), newState);
                Node newNode = new Node(parentNode, action, parentNode.getgCost() + 1, newState);
                parentNode.addChild(action, newNode);
                successors.add(newNode);
            }
        });
        numberOfExpansions++;
        return successors;
    }

    /**
     * this is the number of expansions performed.
     * @param numberOfExpansions
     */
    private void setNumberOfExpansions(int numberOfExpansions) {
        this.numberOfExpansions = numberOfExpansions;
    }

    public void resetExpansions() {
        this.setNumberOfExpansions(0);
    }

    public int getNumberOfExpansions() {
        return numberOfExpansions;
    }
}
