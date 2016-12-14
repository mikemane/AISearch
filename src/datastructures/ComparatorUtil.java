package datastructures;

import datastructures.Node;
import gui.SearchType;
import search.Search;

import java.util.Comparator;

/**
 * Created by un4 on 02/12/16.
 */
public class ComparatorUtil {

//    public enum CompType {
//        GREEDY, ASTAR, UNIFORM;
//    }

    public static Comparator<Node> getComparator(SearchType type) {
        Comparator<Node> comparator = (node, t1) -> {
            switch (type) {
                case GREEDY:
                    return Double.compare(node.gethCost(), t1.gethCost());
                case ASTAR:
                    return Double.compare(node.getfCost(), t1.getfCost());
                case UNIFORM:
                    return Double.compare(node.getgCost(), t1.getgCost());
                default:
                    return 0;
            }
        };
        return comparator;
    }


}
