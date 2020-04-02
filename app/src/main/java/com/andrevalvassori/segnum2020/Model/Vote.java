package com.andrevalvassori.segnum2020.Model;

import java.io.Serializable;

public class Vote implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private boolean upVote;
    private String reason;

    private Event event;

    private User user;

    public Vote() {
    }

    public Vote(int id, boolean upVote, String reason, Event event, User user) {
        super();
        this.id = id;
        this.upVote = upVote;
        this.reason = reason;
        this.event = event;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isUpVote() {
        return upVote;
    }

    public void setUpVote(boolean upVote) {
        this.upVote = upVote;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
