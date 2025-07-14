package com.fit2081.assignment_2.provider;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.fit2081.assignment_2.entities.EventCategory;
import com.fit2081.assignment_2.provider.EventCategoryRepository;
import java.util.List;

public class EventCategoryViewModel extends AndroidViewModel {

    private EventCategoryRepository repository;
    private LiveData<List<EventCategory>> allEventCategories;

    public EventCategoryViewModel(@NonNull Application application) {
        super(application);
        repository = new EventCategoryRepository(application);
        allEventCategories = repository.getAllEventCategories();
    }

    public void insert(EventCategory eventCategory) {
        repository.insert(eventCategory);
    }

    public void update(EventCategory eventCategory) {
        repository.update(eventCategory);
    }

    public void delete(EventCategory eventCategory) {
        repository.delete(eventCategory);
    }

    public void deleteAllEventCategories() {
        repository.deleteAllEventCategories();
    }

    public LiveData<List<EventCategory>> getAllEventCategories() {
        return allEventCategories;
    }
}

