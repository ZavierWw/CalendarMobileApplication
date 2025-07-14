package com.fit2081.assignment_2.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.fit2081.assignment_2.entities.Event;
import java.util.List;

@Dao
public interface EventDao {

    @Insert
    void insert(Event event);

    @Update
    void update(Event event);

    @Delete
    void delete(Event event);

    @Query("DELETE FROM event")
    void deleteAllEvents();

    @Query("SELECT * FROM event ORDER BY id DESC")
    LiveData<List<Event>> getAllEvents();
}

