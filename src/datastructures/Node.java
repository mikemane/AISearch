package datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by mikemane on 04/11/2016.
 */
public class Node implements Comparable {
    private Node parent;
    private Set<Node> children;
    private double gCost;
    private double hCost;
    private double fCost;
    private State state;

    public Node() {
        this.fCost = 0;
    }

    public Node(Node parent, State state) {
        this.parent = parent;
        this.state = state;
        this.setfCost(0);
        this.setgCost(0);
        this.sethCost(0);
    }

    public Node(Node parent,  double cost, State state) {
        setParent(parent);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        if (Double.compare(node.gCost, gCost) != 0) return false;
        if (Double.compare(node.hCost, hCost) != 0) return false;
        if (Double.compare(node.fCost, fCost) != 0) return false;
        if (parent != null ? !parent.equals(node.parent) : node.parent != null) return false;
        if (children != null ? !children.equals(node.children) : node.children != null) return false;
        return state != null ? state.equals(node.state) : node.state == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = parent != null ? parent.hashCode() : 0;
        result = 31 * result + (children != null ? children.hashCode() : 0);
        temp = Double.doubleToLongBits(gCost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(hCost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(fCost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int compareTo(Object o) {
        Node node = ((Node) o);
        return Double.compare(this.getfCost() , node.getfCost());
    }

    public boolean isRoot(){
        return this.parent == null;
    }

    public List<Node> getPath(){
        List<Node> nodes = new ArrayList<>();
        Node currentNode = this;
        while(!currentNode.isRoot()){
            nodes.add(0,currentNode);
            currentNode = currentNode.parent;
        }
        nodes.add(0,currentNode);
        return nodes;
    }

}
