package costfunctions;

import datastructures.Action;
import datastructures.Node;
import datastructures.State;

/**
 * Created by un4 on 29/11/16.
 */
public interface CostFunction {
    double calculateCost(State state , State goalState);
}
