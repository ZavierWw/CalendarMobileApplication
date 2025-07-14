package com.fit2081.assignment_2.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.fit2081.assignment_2.Utils;

@Entity(tableName = "event")
public class Event {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String eventId;
    private String name;
    private String categoryId;
    private int ticketsAvailable;
    private boolean isActive;

    public Event(String name, String categoryId, int ticketsAvailable, boolean isActive) {
        this.eventId = Utils.generateEventId();
        this.name = name;
        this.categoryId = categoryId;
        this.ticketsAvailable = ticketsAvailable;
        this.isActive = isActive;
    }

    @Ignore
    public Event(String name, String categoryId, int ticketsAvailable) {
        this(name, categoryId, ticketsAvailable, true);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
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

    public int getTicketsAvailable() {
        return ticketsAvailable;
    }

    public void setTicketsAvailable(int ticketsAvailable) {
        this.ticketsAvailable = ticketsAvailable;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
