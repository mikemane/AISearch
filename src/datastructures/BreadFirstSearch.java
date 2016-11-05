package datastructures;

import java.util.List;

/**
 * Created by mikemane on 04/11/2016.
 */
public class BreadFirstSearch implements Search {

    private Problem problem;

    public BreadFirstSearch( Problem p) {
        this.problem = new Problem();
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
