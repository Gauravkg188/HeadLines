package com.kg.Headlines.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {SavedArticle.class},version = 1)
public abstract class DatabaseArticles extends RoomDatabase {

    private static DatabaseArticles Instance;

    private static final int NUMBER_OF_THREADS = 3;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public abstract DaoInterface daoInterface();

    public static synchronized DatabaseArticles getInstance(Context context)
    {
        if(Instance ==null)
        {
            Instance= Room.databaseBuilder(context.getApplicationContext(),DatabaseArticles.class,
                    "databaseArticles")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return Instance;
    }




}
