package search;

import datastructures.Action;
import datastructures.NodeExpander;
import datastructures.Problem;
import datastructures.QueuingFunction;

import java.util.List;

/**
 * Created by mikemane on 04/11/2016.
 */
public class BreadFirstSearch implements Search {

    private Problem problem;
    private int metrics;
    private NodeExpander nodeExpander;
    public BreadFirstSearch(Problem p) {
//        this.problem = new Problem()

    }


    @Override
    public Integer getMetric() {
//        return null;
        return metrics;
    }

}
