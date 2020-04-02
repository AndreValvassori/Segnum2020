package com.andrevalvassori.segnum2020.Model;

import com.andrevalvassori.segnum2020.Model.Enums.LocationType;

import java.io.Serializable;

public class Location implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String lx;
    private String ly;
    private Integer type;

    private User user;

    private Event event;

    public Location() {
    }

    public Location(int id, String name, String lx, String ly, Event event) {
        super();
        this.id = id;
        this.name = name;
        this.lx = lx;
        this.ly = ly;
        this.type = LocationType.EVENT.getCode();
        this.event = event;
    }

    public Location(int id, String name, String lx, String ly, Integer type, User user) {
        super();
        this.id = id;
        this.name = name;
        this.lx = lx;
        this.ly = ly;
        this.type = type;
        this.user = user;
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

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public String getLy() {
        return ly;
    }

    public void setLy(String ly) {
        this.ly = ly;
    }

    public LocationType getType() {
        return LocationType.toEnum(type);
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
