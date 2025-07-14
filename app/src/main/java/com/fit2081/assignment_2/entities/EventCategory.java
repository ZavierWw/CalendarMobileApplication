package com.fit2081.assignment_2.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.fit2081.assignment_2.Utils;

@Entity(tableName = "event_category")
public class EventCategory {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String categoryId;
    private int eventCount;
    private boolean isActive;
    private String eventLocation;

    public EventCategory(String name, int eventCount, boolean isActive, String eventLocation) {
        this.categoryId = Utils.generateCategoryId();
        this.name = name;
        this.eventCount = eventCount;
        this.isActive = isActive;
        this.eventLocation = eventLocation;
    }

    @Ignore
    public EventCategory(String name, int eventCount, boolean isActive) {
        this(name, eventCount, isActive, "");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public int getEventCount() {
        return eventCount;
    }

    public void setEventCount(int eventCount) {
        this.eventCount = eventCount;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }
}
