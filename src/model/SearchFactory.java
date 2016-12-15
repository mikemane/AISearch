package model;

import datastructures.ComparatorUtil;
import datastructures.Node;
import gui.SearchType;
import heuristicFunction.HeuristicFunction;
import heuristics.UniformSearchHeuristics;
import search.*;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by un4 on 11/12/16.
 */
public class SearchFactory {

    /**
     * This returns a search variable depending on the selected search type.
     *
     * @param searchType        This is the search type.
     * @param heuristicFunction this is the heuristic fuction used to evaluate the search.
     * @return this returns the search value.
     */
    public Search getSearch(SearchType searchType, HeuristicFunction heuristicFunction) {
        Search search;
        switch (searchType) {
            case ASTAR:
            case GREEDY:
            case UNIFORM:
                search = new GeneralSearch(heuristicFunction, ComparatorUtil.getComparator(searchType));
//                search = new AStarSearch(heuristicFunction);
//                break;
//            case GREEDY:
//                search = new GreedySearch(heuristicFunction);
//                break;
//            case UNIFORM:
//                search = new GeneralSearch(new HeuristicFunction(new UniformSearchHeuristics()), ComparatorUtil.getComparator(ComparatorUtil.CompType.UNIFORM));
                break;
            case BFS:
                search = new BreadFirstSearch();
                break;
            default:
                search = new BreadFirstSearch();
                break;
        }
        return search;
    }

    /**
     * This is the search queue.
     *
     * @param searchType this is the search type.
     * @return this returns the queue.
     */
    public Queue<Node> getQueueFuncton(SearchType searchType) {
        Queue<Node> queueFunction;
        switch (searchType) {
            case BFS:
                queueFunction = new LinkedList<>();
                break;
            case ASTAR:
                queueFunction = new PriorityQueue<>(9, ComparatorUtil.getComparator(SearchType.ASTAR));
                break;
            case GREEDY:
                queueFunction = new PriorityQueue<>(11, ComparatorUtil.getComparator(SearchType.GREEDY));
                break;
            case UNIFORM:
                queueFunction = new PriorityQueue<>(11, ComparatorUtil.getComparator(SearchType.UNIFORM));
                break;
            default:
                queueFunction = new LinkedList<>();
                break;
        }
        return queueFunction;
    }

}
