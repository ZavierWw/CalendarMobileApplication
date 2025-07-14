package com.fit2081.assignment_2.provider;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.fit2081.assignment_2.provider.EventDao;
import com.fit2081.assignment_2.provider.AppDatabase;
import com.fit2081.assignment_2.entities.Event;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventRepository {

    private EventDao eventDao;
    private LiveData<List<Event>> allEvents;
    private ExecutorService executorService;

    public EventRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        eventDao = database.eventDao();
        allEvents = eventDao.getAllEvents();
        executorService = Executors.newFixedThreadPool(2);
    }

    public void insert(Event event) {
        executorService.execute(() -> eventDao.insert(event));
    }

    public void update(Event event) {
        executorService.execute(() -> eventDao.update(event));
    }

    public void delete(Event event) {
        executorService.execute(() -> eventDao.delete(event));
    }

    public void deleteAllEvents() {
        executorService.execute(() -> eventDao.deleteAllEvents());
    }

    public LiveData<List<Event>> getAllEvents() {
        return allEvents;
    }
}

