package com.andrevalvassori.segnum2020.DTO.event;


import com.andrevalvassori.segnum2020.Model.Event;

public class EventSimplifyDTO {

    private int id;
    private String name;

    public EventSimplifyDTO() {
    }

    public EventSimplifyDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public EventSimplifyDTO(Event event) {
        this.id = event.getId();
        this.name = event.getName();
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
