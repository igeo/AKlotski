package com.example.paul.aklotski;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Paul on 3/16/2018.
 */

@Database(entities = {MyGame.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MyGameDao MyGameDao();
}
