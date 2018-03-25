package com.example.paul.aklotski;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Paul on 3/19/2018.
 * information about starting settings of games
 */

public class GameInfo extends Object {

    GameInfo(String s, int p, String n) {
        name =  n;
        string = s;
        par = p;
    }
    GameInfo(String s, int p) {
        this(s, p, "");
    }
    GameInfo(String s) {
        this(s, -1);
    }

    @PrimaryKey
    String string;

    @ColumnInfo
    int par;

    @ColumnInfo
    String name;
}
