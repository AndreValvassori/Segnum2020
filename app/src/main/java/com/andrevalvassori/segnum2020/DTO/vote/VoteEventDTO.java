package com.andrevalvassori.segnum2020.DTO.vote;

import com.andrevalvassori.segnum2020.DTO.event.EventSimplifyDTO;
import com.andrevalvassori.segnum2020.DTO.user.UserSimplifyDTO;
import com.andrevalvassori.segnum2020.Model.Vote;

public class VoteEventDTO {

    private int id;
    private boolean upVote;
    private String reason;
    private UserSimplifyDTO user;

    public VoteEventDTO() {
    }

    public VoteEventDTO(Vote vote) {
        this.id = vote.getId();
        this.upVote = vote.isUpVote();
        this.reason = vote.getReason();
        if (vote.getUser() != null) this.user = new UserSimplifyDTO(vote.getUser());
    }

    public VoteEventDTO(int id, boolean upVote, String reason, EventSimplifyDTO event, UserSimplifyDTO user) {
        this.id = id;
        this.upVote = upVote;
        this.reason = reason;
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

    public UserSimplifyDTO getUser() {
        return user;
    }

    public void setUser(UserSimplifyDTO user) {
        this.user = user;
    }
}
