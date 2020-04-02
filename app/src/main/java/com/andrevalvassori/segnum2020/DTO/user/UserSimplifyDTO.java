package com.andrevalvassori.segnum2020.DTO.user;


import com.andrevalvassori.segnum2020.Model.User;

public class UserSimplifyDTO {

    private int id;
    private String name;

    public UserSimplifyDTO() {
    }

    public UserSimplifyDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserSimplifyDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
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
