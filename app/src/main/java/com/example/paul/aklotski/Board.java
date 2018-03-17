package com.example.paul.aklotski;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Paul on 3/9/2018.
 * This is a board and status
 */

public class Board extends Object {
    public Board(String s[])
    {
        int counter = 0;
        for(int y = 0; y < H; ++y) {
            for(int x = 0; x < W; ++x) {
                if(!Character.isUpperCase((s[y].charAt(x)))) {
                    continue;
                }
                Block block = new Block(Block.c2t(s[y].charAt(x)), x, y);
                if (block.t == Block.Type.K)
                    blocks [0] = block;
                else
                {
                    blocks[++counter] = block;
                }
            }
        }
    }
    enum Direction {U, D, L, R, None};
    public boolean movePiece(int index, Direction d)
    {
        // pupulate board;
        int [][] board = new int [H][W];
        for(int i = 0; i < blocks.length; ++i)
        {
            if( i == index)
                continue;
            Block b = blocks[i];
            for(int y = 0; y < b.H(); ++y)
                for(int x = 0; x < b.W(); ++x)
                    board[b.y+y][b.x+x] = i+1; // so King will also block
        }
        ArrayList<Direction> direction = new ArrayList<Direction>();
        Block b = blocks[index];
        if(b.y > 0)
        {
            boolean open  = true;
            for (int x = 0; x < b.W(); ++x)
                if(board[b.y-1][b.x+x] > 0)
                    open = false;
            if (open)
                direction.add(Direction.U);
        }
        if(b.y+b.H() < H)
        {
            boolean open  = true;
            for (int x = 0; x < b.W(); ++x)
                if(board[b.y+b.H()][b.x+x] > 0)
                    open = false;
            if (open)
                direction.add(Direction.D);
        }
        if(b.x > 0)
        {
            boolean open  = true;
            for (int y = 0; y < b.H(); ++y)
                if(board[b.y+y][b.x-1] > 0)
                    open = false;
            if (open)
                direction.add(Direction.L);
        }
        if(b.x + b.W()< W)
        {
            boolean open  = true;
            for (int y = 0; y < b.H(); ++y)
                if(board[b.y+y][b.x+b.W()] > 0)
                    open = false;
            if (open)
                direction.add(Direction.R);
        }

        if(d == Direction.None && direction.size() > 0) // click, pick one passible move
        {
            Random rand = new Random();
            int value = rand.nextInt(direction.size());
            d = direction.get(value);
        }
        else // swipe, check if the move exist
        {
            if(!direction.contains(d))
                return false;
        }
        switch (d) {
            case U: --b.y; break;
            case D: ++b.y; break;
            case L: --b.x; break;
            case R: ++b.x; break;
        }
        return true;
    }
    boolean hasWon() {
        for(int i = 0; i < blocks.length; ++i){
            if(blocks[i].t == Block.Type.K) {
                if(blocks[i].x == 1 && blocks[i].y == 3)
                    return true;
                else
                    return false;
            }
        }
        return false;
    }
    static final int W = 4;
    static final int H = 5;
    Block[] blocks = new Block[10];
    //char [][] board;
}
