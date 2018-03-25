package com.example.paul.aklotski;

import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Paul on 3/24/2018.
 */

public class solver {
    static Board back_trace(ArrayList<ArrayList<Pair<Board,Integer>>> progress)
    {
        ArrayList<Board> solution = new  ArrayList<Board>();
        Board s;// = progress.back().back().first;
        int idx= progress.get(progress.size()-1).size()-1;
        for(int depth = progress.size() - 1; depth >= 0; --depth)
        {
            ArrayList<Pair<Board,Integer>>front = progress.get(depth);
            Pair<Board,Integer> node = front.get(idx);
            solution.add(node.first);
            idx = node.second;
        }
        return solution.get(solution.size() - 2);
    }
    static Pair<Board, Integer> solve(Board start) {
        if(start.hasWon())
            return new Pair<Board, Integer>(start, 0);
        ArrayList<ArrayList<Pair<Board,Integer>>> progress = new ArrayList<ArrayList<Pair<Board,Integer>>>(); // each vector is a step, index is parent location
        ArrayList<Pair<Board,Integer>> states = new ArrayList<Pair<Board,Integer>>();
        states.add(new Pair<Board,Integer>(start,0));
        progress.add(states);
        //google::dense_hash_set<unsigned long> seen; seen.set_empty_key(0);
        HashSet<String> seen = new HashSet<String>();

        boolean debug = true;
        for(int i = 0; i < 300; ++i)
        {
            if(debug) Log.d("solver", "==== Running step # " + (i + 1) + " ====");
            progress.add(new ArrayList<Pair<Board,Integer>>());
            ArrayList<Pair<Board,Integer>> next = progress.get(progress.size()-1);  // new depth to explore
            ArrayList<Pair<Board,Integer>> current =  progress.get(progress.size()-2); // current depth
            for(int leaf_idx = 0; leaf_idx < current.size(); ++leaf_idx) // loop all current leafs
            {
                ArrayList<Board> ms = current.get(leaf_idx).first.posibleMoves(); // what is poositble next move
                for(Board m : ms)
                {
                    String hashable = m.toString();
                    if(!seen.add(hashable) || !seen.add(m.toString(true)) ) // || !seen.insert(m.getMirror().getHashableL()).second)
                        continue; // donot loop

                    next.add(new  Pair<Board, Integer>(m,leaf_idx));
                    if(m.hasWon())
                    {
                        Log.i("solver", "*** Solved at step " + (i+1));
                        //m.printRepr();
                        int N = 0;
                        for(ArrayList<Pair<Board,Integer>> v : progress)
                        N += v.size();
                        Log.i("solver", "total serched size " + N );
                        return new Pair<Board, Integer>(back_trace(progress), i+1);// back_trace(progress);
                    }
                }
            }
            if(debug) Log.d("solver","==== total leafs " + next.size() );
            if(next.isEmpty())
            {
                Log.i("solver","did not find solution after step " + (i+1));
                break;
            }
        }
        return new Pair<Board, Integer>(start, -1);// no solution
    } // end of solve
}
