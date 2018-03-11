package com.example.paul.aklotski;

/**
 * Created by Paul on 3/9/2018.
 * represent one block piece
 */

public class Block extends Object {
    public Block(Type type) { t = type; }
    public Block(Type type, int xx, int yy) {
        this(type);
        x = xx;
        y = yy;
    }
    public enum Type {K, H, V, P};
    public static Type c2t(char c) {
        switch (c) {
            case 'K': return Type.K;
            case 'H': return Type.H;
            case 'V': return Type.V;
            case 'P': return Type.P;
        }
        return Type.P;
    }
    Type t; // type
    int x; // location x
    int y; // locatoin y
    int W()  { return ( t == Type.K || t == Type.H) ? 2 : 1; } // width
    int H()  { return ( t == Type.K || t == Type.V) ? 2 : 1; } // height
}

