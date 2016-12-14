package model;

import datastructures.GoalTest;
import datastructures.Node;
import datastructures.Problem;
import datastructures.State;
import gui.SearchType;
import heuristicFunction.HeuristicFunction;
import heuristicFunction.UniformHeuristicFunction;
import heuristics.Heuristics;
import heuristics.UniformSearchHeuristics;
import search.BreadFirstSearch;
import search.Search;

import java.util.*;

/**
 * Created by un4 on 11/12/16.
 */
public class SearchModel extends Observable {

    private Search search;
    private Heuristics heuristics;
    private HeuristicFunction heuristicFunction;
    private Problem problem;
    private Stack<HashSet<Node>> nodes;
    private SearchFactory searchFactory;
    private SearchType searchType;
    private String stats;

    public SearchModel(Search search, Heuristics heuristic, State currentState, State goalState) {
        this.search = search;
        this.heuristics = heuristic;
        this.searchType = SearchType.BFS;
        this.problem = new Problem(new GoalTest(goalState), currentState, new UniformHeuristicFunction());
        this.searchFactory = new SearchFactory();
        this.nodes = new Stack<>();
    }


    public SearchModel() {
//        int[] goal = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0};
//        int[] puzzle = new int[]{7, 3, 4, 5, 2, 8, 6, 1, 0};
//        int[] puzzle = new int[]{7, 3, 4, 5, 2, 8, 6, 1, 0};
        this(new BreadFirstSearch(), new UniformSearchHeuristics(), new State(new int[]{7, 3, 4, 5, 2, 8, 6, 1, 0}), new State(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0}));
        ;
    }

//    public void setSearch(Search search) {
//        this.search = search;
//    }

    public void setHeuristics(Heuristics heuristics) {
        this.heuristics = heuristics;
        this.setHeuristicFunction(new HeuristicFunction(heuristics));
        this.setSearch(this.searchType);
    }

//    public void (State currentState) {
//        this.currentState = currentState;
//}


    public void setHeuristicFunction(HeuristicFunction heuristicFunction) {
        this.heuristicFunction = heuristicFunction;
    }

    public HeuristicFunction getHeuristicFunction() {
        return heuristicFunction;
    }

    public void expand(Node node) {
        if (node.getChildren().isEmpty()) return;
        HashSet<Node> nodes = new HashSet<>(node.getChildren().values());
        this.nodes.push(nodes);
        this.setChanged();
        this.notifyObservers();
    }

    public void popStack() {
        if (this.nodes.isEmpty() == false) {
            this.nodes.pop();
            this.setChanged();
            this.notifyObservers();
        }
    }

    public Optional<Set<Node>> getNodes() {
        if (!this.nodes.isEmpty())
            return Optional.of(nodes.lastElement());
        return Optional.empty();
    }


    public void setSearch(SearchType type) {
        this.searchType = type;
        this.search = this.searchFactory.getSearch(type, new HeuristicFunction(this.heuristics));
    }

    public void search() {
        Optional<Node> node = this.search.search(this.problem, this.searchFactory.getQueueFuncton(this.searchType));

        node.ifPresent(n -> {
            this.nodes.clear();
            HashSet<Node> set = new HashSet<>();
            n.setSolutionPath();
            set.add(n.getRoot());
            this.nodes.add(set);

            this.stats = this.searchType + " : " +
                    "\nNo Of Expansions: " + search.getMetric() + " \n" +
                    "Time Spent in Calculating: " + search.timeSpent() + "m/s \n" +
                    "Depth: " + n.getDepth() ;

//            System.out.println(this.searchType);
//            System.out.println(search.getMetric());
//            System.out.println(search.timeSpent());
            this.setChanged();
            this.notifyObservers();
        });
    }

    public String getStats() {
        return stats;
    }

    public void goalState(State state) {
        this.problem.setGoalState(state);
        this.updateObserver();
    }

    public void setStartState(State state){
        this.problem.setInitialState(state);
        this.updateObserver();
    }

    public void updateObserver(){
        this.setChanged();
        this.notifyObservers();
    }

    public State getInitialState(){
        return this.problem.getInitialState();
    }

    public State getGoalState(){
        return this.problem.getGoalState();
    }
}
