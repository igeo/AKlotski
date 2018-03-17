package com.example.paul.aklotski;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Paul on 3/16/2018.
 */

@Dao
public interface MyGameDao {
    @Query("SELECT * FROM MyGame")
    List<MyGame> getAll();

    @Query("SELECT * FROM MyGame WHERE gameId IS :Id")
    List<MyGame> loadAllByGameId(int Id);

    @Insert
    void insert(MyGame game);

    @Delete
    void delete(MyGame game);
}
