package com.example.paul.aklotski;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Paul on 3/19/2018.
 * information about starting settings of games
 */

public class GameInfo extends Object {
    @PrimaryKey
    int String;

    @ColumnInfo
    int par;

    @ColumnInfo
    String name;
}
