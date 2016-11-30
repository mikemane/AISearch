package search;

import datastructures.*;

import java.awt.geom.GeneralPath;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

/**
 * Created by un4 on 29/11/16.
 */
public class GeneralSearch implements Search {


    private HashSet<State> openStateSet;
    private HashSet<State> closedStateSet;
    private PriorityQueue<Node> nodesQueue;
    private ExpandNode expandNode;
    private Problem problem;
    private Node rootNode;
    private double metrics;

    public GeneralSearch(Problem problem) {
        this();
        this.setProblem(problem);
    }

    public GeneralSearch() {
        this.openStateSet = new HashSet<>();
        this.closedStateSet = new HashSet<>();
        this.nodesQueue = new PriorityQueue<>();
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
        this.expandNode = new ExpandNode(problem);
        this.rootNode = new Node(null, null, 0, this.problem.getInitialState());
    }


    @Override
    public Optional<Node> search(Problem problem) {
        this.setProblem(problem);
        clearObjects();
        Node currentNode = this.rootNode;
        this.addToSet(currentNode);
        while (!this.nodesQueue.isEmpty()) {
            Node exNode = this.removeFromSet();
            if (problem.goalState().equals(exNode.getState())) {
                //TODO some metrics here.
                this.metrics = exNode.getfCost();
                return Optional.of(exNode);
            }
            List<Node> nodes = this.expandNode.expandNode(exNode);
            for (Node sNode : nodes) {
                this.addToSet(sNode);
            }
        }
        return Optional.empty();
    }

    private Node removeFromSet() {
        Node node = this.nodesQueue.poll();
        this.closedStateSet.add(node.getState());
        this.openStateSet.remove(node.getState());
        return node;
    }

    private void addToSet(Node node) {
        if (!openStateSet.contains(node.getState()) && !closedStateSet.contains(node.getState())) {
            this.openStateSet.add(node.getState());
            this.nodesQueue.add(node);
        }
    }

    private void clearObjects() {
        this.nodesQueue.clear();
        this.openStateSet.clear();
        this.closedStateSet.clear();
    }


    @Override
    public double getMetric() {
        return this.metrics + numOfExpansions();
    }

    public int numOfExpansions(){
        return this.expandNode.getNumberOfExpansions();
    }

}
