package com.fit2081.assignment_2.provider;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.fit2081.assignment_2.provider.EventCategoryDao;
import com.fit2081.assignment_2.provider.EventDao;
import com.fit2081.assignment_2.entities.EventCategory;
import com.fit2081.assignment_2.entities.Event;

@Database(entities = {EventCategory.class, Event.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract EventCategoryDao eventCategoryDao();
    public abstract EventDao eventDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}


