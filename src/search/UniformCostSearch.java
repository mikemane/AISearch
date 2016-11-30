package search;

import datastructures.Action;
import datastructures.Node;
import datastructures.Problem;

import java.util.List;
import java.util.Optional;

/**
 * Created by udokanwosu on 12/11/2016.
 */
public class UniformCostSearch implements Search {


    @Override
    public Optional<Node> search(Problem problem) {
        return Optional.empty();
    }

    @Override
    public double getMetric() {
        return 0;
    }
}
