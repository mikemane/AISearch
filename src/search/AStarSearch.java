package search;

import datastructures.Action;
import datastructures.Problem;
import datastructures.QueuingFunction;
import heuristics.Heuristics;

import java.util.List;

/**
 * Created by udokanwosu on 12/11/2016.
 */
public class AStarSearch implements Search {
    private Heuristics heuristics;

    public AStarSearch(Heuristics heuristics) {
        this.heuristics = heuristics;
    }


    @Override
    public List<Action> search(Problem problem, QueuingFunction queueingFunction) {
        return null;
    }

    @Override
    public Integer getMetric() {
        return null;
    }
}
