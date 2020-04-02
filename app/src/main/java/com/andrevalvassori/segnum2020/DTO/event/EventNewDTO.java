package com.andrevalvassori.segnum2020.DTO.event;


public class EventNewDTO {

    private int id;
    private String name;
    private String description;
    private int eventTypeId;
    private int userId;

    private String locationX;
    private String locationY;
    private String locationName;

    public EventNewDTO() {
    }

    public EventNewDTO(int id, String name, String description, int eventTypeId, int userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.eventTypeId = eventTypeId;
    }

    public EventNewDTO(int id, String name, String description, int eventTypeId, int userId, String locationName, String locationX, String locationY) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.eventTypeId = eventTypeId;
        this.userId = userId;
        this.locationName = locationName;
        this.locationX = locationX;
        this.locationY = locationY;
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

    public int getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(int eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLocationX() {
        return locationX;
    }

    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    public String getLocationY() {
        return locationY;
    }

    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}

