package com.andrevalvassori.segnum2020.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EventType implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String description;

    private List<Event> events = new ArrayList<>();

    public EventType() {
    }

    public EventType(int id, String name, String description) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return name;
                //+ "-"+ description;
    }
}
