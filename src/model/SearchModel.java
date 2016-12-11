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
    private Problem problem;
    private Stack<HashSet<Node>> nodes;
    private SearchFactory searchFactory;
    private SearchType searchType;


    public SearchModel(Search search, Heuristics heuristic, State currentState, State goalState) {
        this.search = search;
        this.heuristics = heuristic;
        this.searchType = SearchType.BFS;
        this.problem = new Problem(new GoalTest(goalState), currentState, new UniformHeuristicFunction());
        this.searchFactory = new SearchFactory();
        this.nodes = new Stack<>();
    }


    public SearchModel() {
        this(new BreadFirstSearch(), new UniformSearchHeuristics(), new State(new int[]{8, 7, 6, 5, 4, 3, 2, 1, 0}), new State(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0}));
    }

//    public void setSearch(Search search) {
//        this.search = search;
//    }

    public void setHeuristics(Heuristics heuristics) {
        this.heuristics = heuristics;
    }

//    public void (State currentState) {
//        this.currentState = currentState;
//}

    public void expand(Node node) {
        HashSet<Node> nodes = new HashSet<>(node.getChildren().values());
        this.nodes.add(nodes);
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
        this.searchFactory.getSearch(type, new HeuristicFunction(this.heuristics));
    }

    public void search() {
        Optional<Node> node = this.search.search(this.problem, this.searchFactory.getQueueFuncton(this.searchType));
        node.ifPresent(n -> {
            this.nodes.clear();
            HashSet<Node> set = new HashSet<>();
            set.add(n);
            this.nodes.add(set);
            System.out.println(this.searchType);
            System.out.println(search.getMetric());
            System.out.println(search.timeSpent());
            this.setChanged();
            this.notifyObservers();
        });
    }
}
