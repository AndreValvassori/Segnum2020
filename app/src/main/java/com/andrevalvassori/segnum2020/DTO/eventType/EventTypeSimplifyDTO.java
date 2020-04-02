package com.andrevalvassori.segnum2020.DTO.eventType;

import com.andrevalvassori.segnum2020.Model.EventType;

public class EventTypeSimplifyDTO {
    private int id;
    private String name;

    public EventTypeSimplifyDTO() {
    }

    public EventTypeSimplifyDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public EventTypeSimplifyDTO(EventType eventType) {
        this.id = eventType.getId();
        this.name = eventType.getName();
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

    @Override
    public String toString() {
        return "EventTypeSimplifyDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
