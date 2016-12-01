package search;

import datastructures.Action;
import datastructures.Node;
import datastructures.Problem;
import datastructures.State;
import heuristics.Heuristics;

import java.util.*;

/**
 * Created by udokanwosu on 12/11/2016.
 */
public class AStarSearch extends GeneralSearch {
    private Heuristics heuristics;

    public AStarSearch(Heuristics heuristics) {
        super();
        this.heuristics = heuristics;
    }

    @Override
    public Optional<Node> search(Problem problem) {
        this.clearObjects();
        this.rootNode.setgCost(0);
        while (!this.nodesQueue.isEmpty()) {
            Node currentNode = removeFromSet();
            if (this.problem.goalState().equals(currentNode.getState())) {
                return Optional.of(currentNode);
            }
            for (Action direction : Action.values()) {
                if (!currentNode.getState().canMove(direction))
                    continue;
                State newState = currentNode.getState().move(direction);
                double gCost = currentNode.getgCost() + 1;
                double hCost = this.heuristics.getHeuristic(newState,this.problem.goalState()); //this.problem.getPathCostFunction().calculateCost(newState, problem.goalState());
                double fCost = gCost + hCost;
                Node node = new Node(currentNode, fCost, newState);

                this.addToSet(node);

//                if (this.openStateSet.contains(newState))
//                    addToSet(node);
//                else if ()
//                    continue;J
            }
        }
        return Optional.empty();
    }

}
