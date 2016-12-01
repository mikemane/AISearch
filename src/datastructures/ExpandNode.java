package datastructures;

import board.EightPuzzleBoard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by udokanwosu on 12/11/2016.
 */
public class ExpandNode {
    private Problem problem;
    private int numberOfExpansions = 0;

    public ExpandNode(Problem problem) {
        this.problem = problem;
    }


    /**
     * Creates a node with the relevant values.
     *
     * @param parent this is the parent node.
     * @param cost   this is the cost that is associated with this node.
     * @param state  this is the current state of the board.
     * @return the created node.
     */
    private Node createNode(Node parent,  double cost, State state) {
        return new Node(parent, cost, state);
    }

    /**
     * Expands the node gets the successor states.
     *
     * @param parentNode The parent node to get the successor states.
     * @return the list of the children states.
     */
    public List<Node> expandNode(Node parentNode) {
        List<Node> successors = new ArrayList<>();
        for (Action direction : Action.values()) {
            if (parentNode.getState().canMove(direction)) {
                State newState = parentNode.getState().move(direction);
                double cost = this.problem.getPathCostFunction().calculateCost(parentNode.getState(), newState);
                Node newNode = createNode(parentNode,  cost, newState);
                successors.add(newNode);
            }
        }
        numberOfExpansions++;
        return successors;
    }

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
