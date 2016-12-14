package main;

import gui.SearchType;
import heuristicFunction.HeuristicFunction;
import datastructures.ComparatorUtil;
import datastructures.GoalTest;
import datastructures.Node;
import datastructures.Problem;
import datastructures.State;
import gui.GUI;
import heuristics.Heuristics;
import heuristics.ManhattanDistanceHeuristics;
import heuristics.TilesMisplacedHeuristics;
import model.SearchModel;
import search.BreadFirstSearch;
import search.GeneralSearch;
import search.Search;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.PriorityQueue;

/**
 * Created by mikemane on 04/11/2016.
 */
public class Main {


    public static void main(String[] args) {

//        int[] goal = new int[]{6, 4, 7, 8, 5, 0, 3, 2, 1};
//        int[] puzzle = new int[]{5, 6, 7, 4, 0, 8, 3, 2, 1};//
//        int [] puzzle =  new int[]{2, 8, 1, 4, 6, 3, 0, 7, 5};
//        int[] puzzle = new int[]{8, 7, 6, 5, 4, 3, 2, 1, 0};
//        int[] puzzle = new int[]{8, 6, 7, 2, 5, 4, 3, 0, 1};
//        int[] puzzle = new int[]{1, 2, 3, 4, 5, 6, 7, 0, 8};

//        int[] goal = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0};
//        int[] puzzle = new int[]{7, 3, 4, 5, 2, 8, 6, 1, 0};

//        State.SIZE = 4;
//        int[] goal = new int[]{
//                1, 2, 3, 4,
//                5, 6, 7, 8,
//                9, 10, 11, 12,
//                13, 14, 15, 0
//        };
//        int[] puzzle = new int[]{
//                1, 2, 3, 4,
//                5, 6, 7, 8,
//                9, 10, 11, 12,
//                13, 14, 0, 15
//        };

//        int[] goal = {1, 2, 3, 8, 0, 4, 7, 6, 5};
//        int[] puzzle = {5, 6, 7, 4, 0, 8, 3, 2, 1};
//        State puzzleState = new State(puzzle);
//        System.out.println("Problem " + Arrays.toString(puzzle) + "\n\n");
//        GoalTest goalTest = new GoalTest(new State(goal));
//        Problem problem = new Problem(goalTest, puzzleState, new NoCostFunction());

//        Search bfs = new BreadFirstSearch();
//        Optional<Node> result = bfs.search(problem, new LinkedList<>());
//        result.ifPresent(x -> System.out.println("BFS:" + x.getSize()));
//        System.out.println("Breadth first Search: " + bfs.getMetric() + " Number of Expansions \n");
//        System.out.println(bfs.timeSpent());

//        Heuristics heuristics = new ManhattanDistanceHeuristics(); //new TilesMisplacedHeuristics();
//
//        Comparator<Node> aStarComp = ComparatorUtil.getComparator(SearchType.ASTAR);
//        PriorityQueue<Node> aStarQueue = new PriorityQueue<>(9, aStarComp);
//        Search aStar = new GeneralSearch(new HeuristicFunction(heuristics), aStarComp);
//        Optional<Node> aStarResult = aStar.search(problem, aStarQueue);
////        aStarResult.ifPresent(res -> System.out.println(res.getSize()));
//        System.out.println("AStar Search : " + aStar.getMetric() + " Number of expansions \n");
//        System.out.println(aStar.timeSpent());
//
//        Comparator<Node> greedyComp = ComparatorUtil.getComparator(SearchType.GREEDY);
//        PriorityQueue<Node> greedyQueue = new PriorityQueue<>(9, greedyComp);
//        Search greedy = new GeneralSearch(new HeuristicFunction(heuristics), greedyComp);
//        Optional<Node> greedyRes = greedy.search(problem, greedyQueue);
//        greedyRes.ifPresent(res -> System.out.println(res.getDepth()));
//        System.out.println("Greedy Search : " + greedy.getMetric() + " Number of expansions \n");
//        System.out.println(greedy.timeSpent());

//        Comparator<Node> uniformComp = ComparatorUtil.getComparator(ComparatorUtil.CompType.UNIFORM);
//        PriorityQueue<Node> uniformQueue = new PriorityQueue<>(9, uniformComp);
//        Search uniformSearch = new GeneralSearch(new HeuristicFunction(heuristics), uniformComp);
//        Optional<Node> uniformSearchNode = uniformSearch.search(problem, uniformQueue);
////        uniformSearchNode.ifPresent(res -> System.out.println(res.getSize()));
//        System.out.println("Uniform Search: " + uniformSearch.getMetric() + " Number of expansions \n");
//        System.out.println(uniformSearch.timeSpent());

//        GUI gui = new GUI(new SearchModel());
    }
}
