package com.andrevalvassori.segnum2020.Model;

import java.io.Serializable;

public class Commentary implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String comment;

    private Event event;

    private User user;

    public Commentary() {
    }

    public Commentary(int id, String comment, Event event, User user) {
        super();
        this.id = id;
        this.comment = comment;
        this.event = event;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
