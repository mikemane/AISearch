package search;

import datastructures.Action;
import datastructures.Node;
import datastructures.Problem;

import java.util.List;
import java.util.Optional;
import java.util.Queue;

/**
 * Created by mikemane on 04/11/2016.
 */
public interface Search {

    public Optional<Node> search(Problem problem , Queue<Node> queueFunction);
    public double getMetric();

}
