package datastructures;

import java.util.*;

/**
 * Created by mikemane on 04/11/2016.
 */
public class Node implements Comparable {
    private Node parent;
    private HashMap<Action, Node> children;
    private double gCost;
    private double hCost;
    private double fCost;
    private State state;
    private Action action;
    private boolean isSolutionPath;


    public Node() {
        this.fCost = 0;
    }

    public Node(Node parent, Action action, State state) {
        this.parent = parent;
        this.state = state;
        this.setfCost(0);
        this.setgCost(0);
        this.sethCost(0);
        this.setAction(action);
        this.children = new HashMap<>();
        this.isSolutionPath = true;
    }

    public Node(Node parent, Action action, double cost, State state) {
        setParent(parent);
        setgCost(cost);
        setAction(action);
        setState(state);
        this.children = new HashMap<>();
        setSolutionPath(false);
    }

    public void setSolutionPath(boolean solutionPath) {
        isSolutionPath = solutionPath;
    }

    public boolean isSolutionPath() {
        return isSolutionPath;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

    public void setfCost(double fCost) {
        this.fCost = fCost;
    }

    public void setgCost(double gCost) {
        this.gCost = gCost;
    }

    public void sethCost(double hCost) {
        this.hCost = hCost;
    }

    public double getfCost() {
        return fCost;
    }

    public double getgCost() {
        return gCost;
    }

    public double gethCost() {
        return hCost;
    }

    public HashMap<Action, Node> getChildren() {
        return children;
    }

    public Optional<Node> getChildAtAction(Action direction) {
        Node node = this.children.get(direction);
        return Optional.of(node);
    }

    public void setChildren(HashMap<Action, Node> children) {
        this.children = children;
    }

    public void addChild(Action action, Node node) {
        this.children.put(action, node);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }


    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int compareTo(Object o) {
        Node node = ((Node) o);
        return Double.compare(this.getgCost(), node.getgCost());
    }

    public boolean isRoot() {
        return this.parent == null;
    }

    public List<Node> getPath() {
        List<Node> nodes = new ArrayList<>();
        Node currentNode = this;
        while (!currentNode.isRoot()) {
            nodes.add(0, currentNode);
            currentNode = currentNode.parent;
        }
        nodes.add(0, currentNode);
        return nodes;
    }

    public Node getRoot() {
        Node root = this;
        while ((root.parent != null))
            root = root.parent;
        return root;
    }

    public void setSolutionPath() {
        Node current = this;
        while (current.parent != null) {
            current.setSolutionPath(true);
            current = current.parent;
        }
    }

    public int getSize() {
        Node current = this;
        int count = 0;
        while (!current.isRoot()) {
            count++;
            current = current.parent;
        }
        return count;
    }
}
