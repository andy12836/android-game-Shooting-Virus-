package com.example.shootingvirus.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {ScoreEntry.class}, version = 2, exportSchema = false)
public abstract class Database extends RoomDatabase {
    private static final String LOG_TAG = Database.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "db";
    private static Database sInstance;

    public static Database getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        Database.class, Database.DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return sInstance;
    }

    public abstract ScoreDao Dao();
}
