package datastructures;

import java.util.List;

/**
 * Created by mikemane on 04/11/2016.
 */
public interface Search {

    public List<Action> search(Problem problem, QueuingFunction queueingFunction);
    public Integer getMetric();

}
