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
        this.setProblem(problem);
        this.clearObjects();

        this.rootNode.setgCost(0);
        this.rootNode.sethCost(this.heuristics.getHeuristic(this.rootNode.getState(), problem.goalState()));
        this.add(rootNode);

        while (!this.queueNode.isEmpty()) {
            Node currentNode = removeTopNode();
            if (this.problem.goalState().equals(currentNode.getState())) {
                return Optional.of(currentNode);
            }

            for (Action direction : Action.values()) {
                if (!currentNode.getState().canMove(direction))
                    continue;
                State newState = currentNode.getState().move(direction);
                Node node = new Node(currentNode, newState);
                if (contains(node)) continue;

                double gCost = currentNode.getgCost();
                double hCost = this.heuristics.getHeuristic(newState, this.problem.goalState()); //this.problem.getPathCostFunction().calculateCost(newState, problem.goalState());
                double fCost = gCost + hCost;

                node.setfCost(fCost);
                node.setgCost(gCost);
                node.sethCost(hCost);

                this.add(node);
                this.metrics++;
            }
        }
        return Optional.empty();
    }



}
