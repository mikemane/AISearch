package test;

import datastructures.Node;
import datastructures.State;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by un4 on 29/11/16.
 */
public class NodeTest {
    @Test
    public void equals() throws Exception {
        State state = new State(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0});
        State state1 = new State(new int[]{1, 2, 3, 4, 5, 6, 7, 0, 8});

        Node node = new Node(null, null, 1, state);
        Node node1 = new Node(null, null, 1, state);
        Node node2 = new Node(null, null, 1, state1);
        assertEquals(node, node1);
        assertNotEquals(node, node2);
        assertNotEquals(node1, node2);

    }


}