package search;

import datastructures.*;

import java.util.*;

/**
 * Created by mikemane on 04/11/2016.
 */
public class BreadFirstSearch implements Search {
    private Problem problem;
    private double metrics;
    private ExpandNode expandNode;
    private Queue<Node> queue;
    private HashSet<State> closedStateSet;
    private HashSet<State> openStateSet;
    private Node rootNode;

    public BreadFirstSearch(Problem p) {
        this();
        this.problem = p;
        this.expandNode = new ExpandNode(p);
        this.rootNode = new Node(null, null, 0, problem.getInitialState());
    }

    public BreadFirstSearch() {
        this.openStateSet = new HashSet<>();
        this.closedStateSet = new HashSet<>();
        this.queue = new PriorityQueue<>();
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
        this.expandNode = new ExpandNode(problem);
    }

    @Override
    public Optional<Node> search(Problem problem) {
        this.setProblem(problem);
        this.rootNode = new Node(null,problem.getInitialState());
        return this.bfs();
    }


    public Optional<Node> bfs() {
        this.closedStateSet.clear();
        this.queue.clear();
        this.openStateSet.clear();
        Node node = this.rootNode;
        this.addToSet(node);
        while (!queue.isEmpty()) {
            Node eNode = this.removeFirstFromSet();
            if (problem.goalState().equals(eNode.getState())) {
                this.metrics = eNode.getfCost();
                return Optional.of(eNode);
            }
            List<Node> nodes = this.expandNode.expandNode(eNode);
            for (Node sNode : nodes) {
                this.addToSet(sNode);
            }
        }
        return Optional.empty();
    }


    @Override
    public double getMetric() {
        return metrics + this.expandNode.getNumberOfExpansions();
    }

    private Node removeFirstFromSet() {
        Node node = this.queue.poll();
        this.closedStateSet.add(node.getState());
        this.openStateSet.remove(node.getState());
        return node;
    }

    private void addToSet(Node node) {
        if (!openStateSet.contains(node.getState()) && !closedStateSet.contains(node.getState())) {
            this.openStateSet.add(node.getState());
            this.queue.add(node);
        }
    }
}
