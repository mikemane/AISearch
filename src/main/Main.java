package main;

import datastructures.*;
import gui.SearchType;
import heuristicFunction.HeuristicFunction;
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
        GUI gui = new GUI(new SearchModel());
    }
}
