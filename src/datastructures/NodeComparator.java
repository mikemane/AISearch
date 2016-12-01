package datastructures;

import java.util.Comparator;

/**
 * Created by udokanwosu on 01/12/2016.
 */

public class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        return Double.compare(o1.getfCost(), o2.getfCost());
    }
}

