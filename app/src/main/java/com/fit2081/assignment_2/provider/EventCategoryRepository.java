package com.fit2081.assignment_2.provider;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.fit2081.assignment_2.provider.EventCategoryDao;
import com.fit2081.assignment_2.provider.AppDatabase;
import com.fit2081.assignment_2.entities.EventCategory;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventCategoryRepository {

    private EventCategoryDao eventCategoryDao;
    private LiveData<List<EventCategory>> allEventCategories;
    private ExecutorService executorService;

    public EventCategoryRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        eventCategoryDao = database.eventCategoryDao();
        allEventCategories = eventCategoryDao.getAllEventCategories();
        executorService = Executors.newFixedThreadPool(2);
    }

    public void insert(EventCategory eventCategory) {
        executorService.execute(() -> eventCategoryDao.insert(eventCategory));
    }

    public void update(EventCategory eventCategory) {
        executorService.execute(() -> eventCategoryDao.update(eventCategory));
    }

    public void delete(EventCategory eventCategory) {
        executorService.execute(() -> eventCategoryDao.delete(eventCategory));
    }

    public void deleteAllEventCategories() {
        executorService.execute(() -> eventCategoryDao.deleteAllEventCategories());
    }

    public LiveData<List<EventCategory>> getAllEventCategories() {
        return allEventCategories;
    }
}

