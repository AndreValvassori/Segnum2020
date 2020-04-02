package com.andrevalvassori.segnum2020.DTO.user;


public class ChangeProfileDTO {

    private int profileId;

    public ChangeProfileDTO() {
    }

    public ChangeProfileDTO(int profileId) {
        this.profileId = profileId;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }
}
