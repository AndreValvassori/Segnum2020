package com.andrevalvassori.segnum2020.DTO.commentary;

import com.andrevalvassori.segnum2020.DTO.event.EventSimplifyDTO;
import com.andrevalvassori.segnum2020.DTO.user.UserSimplifyDTO;
import com.andrevalvassori.segnum2020.Model.Commentary;

public class CommentaryUserDTO {

    private int id;
    private String comment;
    private EventSimplifyDTO event;

    public CommentaryUserDTO() {
    }

    public CommentaryUserDTO(Commentary commentary) {
        this.id = commentary.getId();
        this.comment = commentary.getComment();
        if (commentary.getEvent() != null) this.event = new EventSimplifyDTO(commentary.getEvent());
    }

    public CommentaryUserDTO(int id, String comment, EventSimplifyDTO event, UserSimplifyDTO user) {
        this.id = id;
        this.comment = comment;
        this.event = event;
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
}
