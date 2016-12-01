package search;

import datastructures.*;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by un4 on 29/11/16.
 */
public abstract class GeneralSearch implements Search {


    protected HashSet<State> openSet;
    protected HashSet<State> closedSet;
    protected PriorityQueue<Node> queueNode;
    protected Problem problem;
    protected Node rootNode;
    protected double metrics;

    public GeneralSearch(Problem problem) {
        this();
        this.setProblem(problem);
    }

    public GeneralSearch() {
        this.closedSet = new HashSet<>();
        this.openSet = new HashSet<>();
        Comparator<Node> comparator = new NodeComparator();
        this.queueNode = new PriorityQueue<>(11, comparator);
        this.metrics = 0;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
        this.rootNode = new Node(null, 0, this.problem.getInitialState());
    }

    protected void add(Node node) {
        if (!contains(node)) {
            this.queueNode.add(node);
            this.openSet.add(node.getState());
        }
    }

    protected void clearObjects() {
        this.queueNode.clear();
        this.openSet.clear();
        this.closedSet.clear();
    }

    protected Node removeTopNode() {
        Node node = this.queueNode.poll();
        this.closedSet.add(node.getState());
        this.openSet.remove(node.getState());
        return node;
    }

    @Override
    public double getMetric() {
        return this.metrics;
    }


    public boolean contains(Node node) {
        return this.openSet.contains(node.getState()) || this.closedSet.contains(node.getState());
    }


}
