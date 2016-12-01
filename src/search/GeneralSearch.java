package search;

import datastructures.ExpandNode;
import datastructures.Node;
import datastructures.Problem;
import datastructures.State;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by un4 on 29/11/16.
 */
public abstract class GeneralSearch implements Search {


    protected HashSet<State> openStateSet;
    protected HashSet<State> closedStateSet;
    protected PriorityQueue<Node> nodesQueue;
    protected ExpandNode expandNode;
    protected Problem problem;
    protected Node rootNode;
    protected double metrics;

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
        this.rootNode = new Node(null, 0, this.problem.getInitialState());
    }


//    @Override
//    public Optional<Node> search(Problem problem) {
//        this.setProblem(problem);
//        clearObjects();
//        Node currentNode = this.rootNode;
//        this.addToSet(currentNode);
//        while (!this.nodesQueue.isEmpty()) {
//            Node exNode = this.removeFromSet();
//            if (problem.goalState().equals(exNode.getState())) {
//                //TODO some metrics here.
//                this.metrics = exNode.getfCost();
//                return Optional.of(exNode);
//            }
//            List<Node> nodes = this.expandNode.expandNode(exNode);
//            for (Node sNode : nodes) {
//                this.addToSet(sNode);
//            }
//        }
//        return Optional.empty();
//    }

    protected Node removeFromSet() {
        Node node = this.nodesQueue.poll();
        this.closedStateSet.add(node.getState());
        this.openStateSet.remove(node.getState());
        return node;
    }

    protected void addToSet(Node node) {
        if (!openStateSet.contains(node.getState()) && !closedStateSet.contains(node.getState())) {
            this.openStateSet.add(node.getState());
            this.nodesQueue.add(node);
        }
    }

    protected void clearObjects() {
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

    public boolean contains(State state){
        return this.openStateSet.contains(state) || this.closedStateSet.contains(state);
    }


}
