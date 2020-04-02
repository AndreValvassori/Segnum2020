package com.andrevalvassori.segnum2020.DTO.commentary;


import com.andrevalvassori.segnum2020.DTO.event.EventSimplifyDTO;
import com.andrevalvassori.segnum2020.DTO.user.UserSimplifyDTO;
import com.andrevalvassori.segnum2020.Model.Commentary;

public class CommentaryEventDTO {

    private int id;
    private String comment;
    private UserSimplifyDTO user;

    public CommentaryEventDTO() {
    }

    public CommentaryEventDTO(Commentary commentary) {
        this.id = commentary.getId();
        this.comment = commentary.getComment();
        if (commentary.getUser() != null) this.user = new UserSimplifyDTO(commentary.getUser());
    }

    public CommentaryEventDTO(int id, String comment, EventSimplifyDTO event, UserSimplifyDTO user) {
        this.id = id;
        this.comment = comment;
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

    public UserSimplifyDTO getUser() {
        return user;
    }

    public void setUser(UserSimplifyDTO user) {
        this.user = user;
    }
}
