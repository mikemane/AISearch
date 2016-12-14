package search;

import gui.SearchType;
import heuristicFunction.HeuristicFunction;
import datastructures.ComparatorUtil;

/**
 * Created by un4 on 02/12/16.
 */
public class AStarSearch extends GeneralSearch {
    public AStarSearch(HeuristicFunction heuristicFunction) {
        super(heuristicFunction, ComparatorUtil.getComparator(SearchType.ASTAR));
    }
}
