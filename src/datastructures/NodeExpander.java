package datastructures;

import board.EightPuzzleBoard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by udokanwosu on 12/11/2016.
 */
public class NodeExpander {


//    private EightPuzzleBoard board;
    private HashSet<Node> closedSet;

    public NodeExpander(EightPuzzleBoard board) {

    }


    private Node createNode(Node parent ,Action action, double cost, int [] state){
        return new Node(parent,action,cost,state);
    }

    public List<Node> expandNode(Node node) {
//        Node parent = new Node(null, this.board.getState());
//        List<Node> successors = new ArrayList<>();
//        for (Action direction : Action.values()) {
//            if (board.canMove(direction)) {
//                board.move(direction);
//                successors.add(new Node(node, board.getState()));
//            }
//        }
//        return successors;
        return null;
    }


}
