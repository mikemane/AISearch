package datastructures.test;

import datastructures.Node;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by mikemane on 05/11/2016.
 */
public class NodeTest {

    @Test
    public void checkEquality() {
        int[] state1 = {1, 2, 3, 4, 5};
        int[] state2 = {5, 4, 3, 2, 1};
        Node node = new Node();
        node.setState(state1);

        Node node2 = new Node();
        node2.setState(state1);

        Node node3 = new Node();
        node3.setState(state2);

        assertEquals(node, node2);
        assertNotEquals(node, node3);
        assertNotEquals(node2, node3);
    }

}