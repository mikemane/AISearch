package main;

import costfunctions.AStarCostFunction;
import costfunctions.BreadthFirstSearchCostFunction;
import datastructures.*;
import heuristics.ManhattanDistanceHeuristics;
import search.AStarSearch;
import search.BreadFirstSearch;
import search.GeneralSearch;
import search.Search;

import java.util.Optional;

/**
 * Created by mikemane on 04/11/2016.
 */
public class Main {


    public static void main(String[] args) {

        int[] goal = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0};
        int[] puzzle = new int[]{8, 7, 6, 5, 4, 3, 2, 1, 0};
        State puzzleState = new State(puzzle);

        GoalTest goalTest = new GoalTest(new State(goal));
        Problem problem = new Problem(goalTest, puzzleState, new BreadthFirstSearchCostFunction());
        Search bfs = new BreadFirstSearch();
        Optional<Node> result = bfs.search(problem);
        result.ifPresent(x -> System.out.println(x.getState()));
        System.out.println(bfs.getMetric());


        System.out.println("\n\n A STAR \n\n");

//        AStarCostFunction aStarCostFunction = new AStarCostFunction(new ());
        Problem problem1 = new Problem(goalTest , puzzleState, new BreadthFirstSearchCostFunction());
        Search aStarSearch = new AStarSearch(new ManhattanDistanceHeuristics());
        Optional<Node> result1 = aStarSearch.search(problem1);
        result.ifPresent( r -> System.out.println(r.getState()));
        System.out.println(aStarSearch.getMetric());
    }

}
