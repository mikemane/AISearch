import datastructures.Action;
import datastructures.Node;
import datastructures.NodeExpander;
import datastructures.Problem;

import java.util.HashSet;
import java.util.List;
import java.util.Queue;

/**
 * Created by udokanwosu on 12/11/2016.
 */
public class GraphSearch {


    HashSet<Node> openSet;
    HashSet<Node> closedSet;

    NodeExpander nodeExpander;

    public GraphSearch(){
        nodeExpander = new NodeExpander();
    }

    public List<Action> search(Problem problem , Queue<Node> frontierNode){
//        this

    }


}
