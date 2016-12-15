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

    /**
     * This is the search model that represents the data type for the calendar.
     * @param search this is the type of search.
     * @param heuristic this is the heuristic function for the search.
     * @param currentState this is the current stage of the game.
     * @param goalState this is the goal state of the game.
     */
    public SearchModel(Search search, Heuristics heuristic, State currentState, State goalState) {
        this.search = search;
        this.heuristics = heuristic;
        this.searchType = SearchType.BFS;
        this.problem = new Problem(new GoalTest(goalState), currentState, new UniformHeuristicFunction());
        this.searchFactory = new SearchFactory();
        this.nodes = new Stack<>();
    }


    public SearchModel() {
        this(new BreadFirstSearch(), new UniformSearchHeuristics(), new State(new int [] {
            6, 1, 10, 2,
                    7, 11, 4, 14,
                    5, 0, 9, 15,
                    8, 12, 13, 3
        }),new State(new int[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 0
        }));
    }

    public void setHeuristics(Heuristics heuristics) {
        this.heuristics = heuristics;
        this.setHeuristicFunction(new HeuristicFunction(heuristics));
        this.setSearch(this.searchType);
    }


    public void setHeuristicFunction(HeuristicFunction heuristicFunction) {
        this.heuristicFunction = heuristicFunction;
    }

    public HeuristicFunction getHeuristicFunction() {
        return heuristicFunction;
    }

    /**
     * This is the node to expand.
     * @param node this is the node to add to the queue.
     */
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

    /**
     * This is the search operator.
     */
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

    /**
     * This returns the stats of the game.
     * @return
     */
    public String getStats() {
        return stats;
    }

    /**
     * This sets the goal state.
     * @param state
     */
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
