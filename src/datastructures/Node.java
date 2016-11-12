package datastructures;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by mikemane on 04/11/2016.
 */
public class Node {
    private Node parent;
    private Set<Node> children;
    private double gCost;
    private double hCost;
    private double fCost;
    private int[] state;
    private Action action;

    public Node() {
        this.fCost = 0;
    }

    public Node(Node parent, int[] state) {
        this.parent = parent;
        this.state = state;
        this.fCost = 0;
    }

    public Node(Node parent, Action action, double cost, int[] state) {
        setParent(parent);
        setAction(action);
        setfCost(cost);
        setState(state);
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

    public void setChildren(Set<Node> children) {
        this.children = children;
    }

    public Set<Node> getChildren() {
        return this.children;
    }

    public int[] getState() {
        return state;
    }

    public void setState(int[] state) {
        this.state = state;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object obj) {
        Node node = (Node) obj;
        return Arrays.equals(this.getState(), ((Node) obj).getState());
    }
}
