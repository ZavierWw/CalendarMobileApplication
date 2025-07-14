package com.fit2081.assignment_2.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.fit2081.assignment_2.entities.EventCategory;
import java.util.List;

@Dao
public interface EventCategoryDao {

    @Insert
    void insert(EventCategory eventCategory);

    @Update
    void update(EventCategory eventCategory);

    @Delete
    void delete(EventCategory eventCategory);

    @Query("DELETE FROM event_category")
    void deleteAllEventCategories();

    @Query("SELECT * FROM event_category ORDER BY id DESC")
    LiveData<List<EventCategory>> getAllEventCategories();
}

