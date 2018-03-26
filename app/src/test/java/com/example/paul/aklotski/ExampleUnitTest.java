package com.example.paul.aklotski;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void solver() throws Exception {
        Board start = new Board(new String("PVVVPvvvHhHhPPKk  kk"));
        int steps = solver.solve(start).second;
        assertEquals(116, steps);
    }
}