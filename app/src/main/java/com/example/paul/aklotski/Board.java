package com.example.paul.aklotski;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Paul on 3/9/2018.
 * This is a board and status
 */

public class Board extends Object {
    public Board() {};
    public Board(String s) {
        String ss[] = new String[H];
        for(int i = 0; i< H; ++i)
            ss[i] = s.substring(i*W, i*W+W);
        init(ss);
    }
    public Board(String s[]) {
        init(s);
    }
    private void init(String s[])
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
    public Board clone() {
        Board cloned = new Board();
        cloned.blocks = new Block[blocks.length];
        for(int i = 0; i < blocks.length; ++i)
            cloned.blocks[i] = blocks[i].clone();
        return cloned;
    }
    enum Direction {U, D, L, R, None};
    public ArrayList<Direction> posibleMoveDirections(int index)
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
        return direction;
    }

    public boolean movePiece(int index, Direction d)
    {
        ArrayList<Direction> direction = posibleMoveDirections(index);
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
        Block b = blocks[index];
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

    public ArrayList<Board> posibleMoves(){
        ArrayList<Board> moves =  new  ArrayList<Board>();
        for(int i = 0; i < blocks.length; ++i) {
            ArrayList<Direction> directions = posibleMoveDirections(i);
            for(Direction d : directions) {
                Board b = this.clone();
                if(b.movePiece(i, d))
                    moves.add(b);
            }
        }
        return moves;
    }

    @Override
    public String toString(){
        return toString(false);
    }

    public String toString(boolean mirror){
        char[] s = new char[W*H];
        for(int i = 0; i < s.length; ++i)
            s[i] = ' ';
        for(int i = 0; i < blocks.length; ++i) {
            Block b = blocks[i];
            for(int x = b.x; x < b.x + b.W(); ++x)
                for(int y = b.y; y < b.y + b.H(); ++y)
                    s[y*W + (mirror ? W-1-x: x)] = b.t.toString().charAt(0);
        }// end loop blocks
        return new String(s);
    }
    static final int W = 4;
    static final int H = 5;
    Block[] blocks = new Block[10];
}
