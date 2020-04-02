package com.andrevalvassori.segnum2020.Model.Enums;

public enum Profile {

    MASTER(1, "ROLE_MASTER"),
    ADMINISTRATOR(2, "ROLE_ADMIN"),
    MODERATOR(3, "ROLE_MODERADOR"),
    USER(4, "ROLE_USER");

    public int code;
    private String description;

    private Profile(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Profile toEnum(int code) {

        if(code == 0) {
            return null;
        }

        for(Profile x : Profile.values()) {
            if(code == x.getCode()) {
                return x;
            }
        }
        throw new IllegalArgumentException("Illegal Code: " + code);
    }
}