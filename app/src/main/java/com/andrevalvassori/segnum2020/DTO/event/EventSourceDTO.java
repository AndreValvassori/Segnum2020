package com.andrevalvassori.segnum2020.DTO.event;

import com.andrevalvassori.segnum2020.Model.Enums.EventSource;

public class EventSourceDTO {

    private int id;
    private String name;

    public EventSourceDTO() {
    }

    public EventSourceDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public EventSourceDTO(EventSource source) {
        this.id = source.getCode();
        this.name = source.name();
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
}
