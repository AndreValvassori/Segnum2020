package com.andrevalvassori.segnum2020.DTO.event;


public class EventNewSimplifyDTO {

    private int id;
    private String name;
    private String description;
    private int eventTypeId;
    private int userId;

    public EventNewSimplifyDTO() {
    }

    public EventNewSimplifyDTO(int id, String name, String description, int eventTypeId, int userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.eventTypeId = eventTypeId;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(int eventTypeId) {
        this.eventTypeId = eventTypeId;
    }
}

