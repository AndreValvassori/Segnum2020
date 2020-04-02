package com.andrevalvassori.segnum2020.DTO.commentary;

public class CommentaryNewDTO {

    private String comment;
    private int eventId;
    private int userId;

    public CommentaryNewDTO() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
