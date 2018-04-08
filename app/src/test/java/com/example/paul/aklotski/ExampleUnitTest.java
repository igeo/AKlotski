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
    public void solver_isCorrect() throws Exception {
        //Profiler.start("Solver");
        Board start = new Board("VKkVvkkvVHhVvPPvP  P");
        int steps = solver.solve(start).size() - 1;
        assertEquals(116, steps);
    }
}