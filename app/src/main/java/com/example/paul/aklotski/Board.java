package com.example.paul.aklotski;

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
    static final int W = 4;
    static final int H = 5;
    Block[] blocks = new Block[10];
    char [][] board;
}
