package test;

import datastructures.Action;
import datastructures.State;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by un4 on 29/11/16.
 */
public class StateTest {

    int[] s1 = new int[]{1, 2, 3, 4, 0, 6, 7, 8, 5};

    @Test
    public void getPositionOf() throws Exception {

    }

    @Test
    public void canMove() throws Exception {

    }

    @Test
    public void move() throws Exception {

    }

    @Test
    public void moveGapUp() throws Exception {
        State state = new State(s1);
        State state2 = new State(s1);

        State up1 = state.move(Action.UP);
        State up2 = state2.move(Action.UP);

        assertEquals(up1, up2);
        State moveDown = state.move(Action.DOWN);
        State moveUp = state2.move(Action.UP);
        assertNotEquals(moveUp, moveDown);
    }

    @Test
    public void moveGapLeft() throws Exception {
        State state = new State(s1);
        State state2 = new State(s1);
        assertEquals(state.move(Action.LEFT), state.move(Action.LEFT));
    }

    @Test
    public void moveGapRight() throws Exception {
        State state = new State(new int[]{1, 2, 3, 4, 5, 6, 7 , 0 , 8}).move(Action.RIGHT);
        State state2 = new State(new int[]{1, 2, 3, 4, 5, 6, 7 , 0 , 8}).move(Action.RIGHT);
        assertEquals(state, state2);
//        assertEquals();
    }

    @Test
    public void equalStates() throws Exception {
        State state = new State(s1);
        State state1 = new State(s1);
        State state2 = new State(new int[]{1, 2, 3, 4, 5, 6, 7, 0, 8});

        assertTrue(state.equals(state1));
        assertFalse(state.equals(state2));

        assertEquals(state, state1);
        assertNotEquals(state,state2);
        assertNotEquals(state1,state2);
    }

}