package com.example.paul.aklotski;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Paul on 3/16/2018.
 * the record of my game play
 */
@Entity //(tableName = "my_game")
public class MyGame extends Object {
    @PrimaryKey (autoGenerate = true)
    int sn;

    @ColumnInfo
    int gameId;

    @ColumnInfo
    int steps;

    @ColumnInfo
    boolean won;

    @ColumnInfo
    boolean solved;

    @ColumnInfo
    int timestamp;

    @ColumnInfo
    int time_spent;

    //@ColumnInfo(name = "first_name")
    //private String firstName;
}
