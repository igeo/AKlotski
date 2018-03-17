package com.example.paul.aklotski;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Paul on 3/16/2018.
 */

@Database(entities = {GamePlayed.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GamePlayedDao MyGameDao();
}
