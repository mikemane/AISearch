package search;

import HeuristicFunction.HeuristicFunction;
import datastructures.ComparatorUtil;
import datastructures.Node;

import java.util.Comparator;

/**
 * Created by un4 on 02/12/16.
 */
public class AStarSearch extends GeneralSearch {
    public AStarSearch(HeuristicFunction heuristicFunction) {
        super(heuristicFunction, ComparatorUtil.getComparator(ComparatorUtil.CompType.ASTAR));
    }
}
