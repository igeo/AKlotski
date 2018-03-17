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
public interface GamePlayedDao {
    @Query("SELECT * FROM GamePlayed")
    List<GamePlayed> getAll();

    @Query("SELECT * FROM GamePlayed WHERE gameId IS :Id")
    List<GamePlayed> loadAllByGameId(int Id);

    @Insert
    void insert(GamePlayed game);

    @Delete
    void delete(GamePlayed game);
}
