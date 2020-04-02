package com.andrevalvassori.segnum2020.DTO.user;

import com.andrevalvassori.segnum2020.Model.Enums.Profile;

public class ProfileDTO {

    private int id;
    private String name;

    public ProfileDTO() {
    }

    public ProfileDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProfileDTO(Profile profile) {
        this.id = profile.getCode();
        this.name = profile.getDescription();
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
}
