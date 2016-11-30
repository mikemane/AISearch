package search;

import datastructures.Action;
import datastructures.Node;
import datastructures.Problem;
import heuristics.Heuristics;

import java.util.List;
import java.util.Optional;

/**
 * Created by udokanwosu on 12/11/2016.
 */
public class AStarSearch implements Search {
    private Heuristics heuristics;

    public AStarSearch(Heuristics heuristics) {
        this.heuristics = heuristics;
    }

    @Override
    public Optional<Node> search(Problem problem) {
        return Optional.empty();
    }

    @Override
    public double getMetric() {
        return 0;
    }
}
