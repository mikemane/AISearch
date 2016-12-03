package search;

import HeuristicFunction.HeuristicFunction;
import costfunctions.CostFunction;
import datastructures.*;

import java.util.*;

/**
 * Created by un4 on 29/11/16.
 */
public class GeneralSearch implements Search {
    protected HashSet<State> closedSet;
    protected Queue<Node> queueNode;
    protected Problem problem;
    protected Node rootNode;
    protected double metrics;
    protected ExpandNode expandNode;
    protected HeuristicFunction heuristicFunction;
    protected HashMap<State, Node> openSet;
    protected Comparator<Node> nodeComparator;

    public GeneralSearch(Comparator<Node> nodeComparator) {
        this.closedSet = new HashSet<>();
        this.openSet = new HashMap<>();
        this.metrics = 0;
        this.nodeComparator = nodeComparator;
    }

    public GeneralSearch(HeuristicFunction costFunction, Comparator<Node> comparator) {
        this(comparator);
        this.heuristicFunction = costFunction;

    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }


    @Override
    public Optional<Node> search(Problem problem, Queue<Node> queueFunction) {
        this.queueNode = queueFunction;
        this.setProblem(problem);
        this.expandNode = new ExpandNode(problem);
        Node node = new Node(null, 0, problem.getInitialState());

        this.clearObjects();
        this.add(node);
        while (!queueNode.isEmpty()) {
            Node currentNode = this.removeTopNode();
            if (problem.goalState().equals(currentNode.getState())) {
                return Optional.of(currentNode);
            }

            this.expandNode.expandNode(currentNode).forEach(n -> {
                double hCost = this.heuristicFunction.calculateCost(n.getState(), problem.goalState());
                n.sethCost(hCost);
                n.setfCost(currentNode.getgCost() + n.gethCost());
                this.add(n);
            });
        }
        return Optional.empty();

    }


    protected void add(Node node) {
        if (!contains(node)) {
            if (this.openSet.containsKey(node.getState())) {
                Node updateNode = this.openSet.get(node.getState());
                if (nodeComparator.compare(node, updateNode) < 0) {
                    if (queueNode.remove(updateNode)) {
                        openSet.remove(updateNode.getState());
                    }
                    this.openSet.put(node.getState(), node);
                    this.queueNode.add(node);
                }
            } else {
                this.queueNode.add(node);
                this.openSet.put(node.getState(), node);
            }
        }
    }

    protected void clearObjects() {
        this.queueNode.clear();
        this.closedSet.clear();
    }

    protected Node removeTopNode() {
        this.metrics++;
        Node node = this.queueNode.poll();
        this.openSet.remove(node.getState());
        this.closedSet.add(node.getState());
        return node;
    }

    @Override
    public double getMetric() {
        return this.metrics;
    }

    public boolean contains(Node node) {
        return this.closedSet.contains(node.getState());
    }

}
