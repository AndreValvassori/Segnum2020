package com.andrevalvassori.segnum2020.DTO.commentary;


import com.andrevalvassori.segnum2020.DTO.event.EventSimplifyDTO;
import com.andrevalvassori.segnum2020.DTO.user.UserSimplifyDTO;
import com.andrevalvassori.segnum2020.Model.Commentary;

public class CommentaryDTO {

    private int id;
    private String comment;
    private EventSimplifyDTO event;
    private UserSimplifyDTO user;

    public CommentaryDTO() {
    }

    public CommentaryDTO(Commentary commentary) {
        this.id = commentary.getId();
        this.comment = commentary.getComment();
        if (commentary.getEvent() != null) this.event = new EventSimplifyDTO(commentary.getEvent());
        if (commentary.getUser() != null) this.user = new UserSimplifyDTO(commentary.getUser());
    }

    public CommentaryDTO(int id, String comment, EventSimplifyDTO event, UserSimplifyDTO user) {
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

    public EventSimplifyDTO getEvent() {
        return event;
    }

    public void setEvent(EventSimplifyDTO event) {
        this.event = event;
    }

    public UserSimplifyDTO getUser() {
        return user;
    }

    public void setUser(UserSimplifyDTO user) {
        this.user = user;
    }
}
