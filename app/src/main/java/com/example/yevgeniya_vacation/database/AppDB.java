//Student Name: Yevgeniya Anasheva
//Student ID: 119338192
package com.example.yevgeniya_vacation.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CountryEntity.class}, version = 1, exportSchema = false)
public abstract class AppDB extends RoomDatabase {
    private static volatile AppDB db;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract CountryDAO countryDAO();

    public static AppDB getDatabase(final Context context) {
        if (db == null) {
            //build the database
            db = Room.databaseBuilder(context.getApplicationContext(), AppDB.class, "db_countries")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return db;
    }
}
