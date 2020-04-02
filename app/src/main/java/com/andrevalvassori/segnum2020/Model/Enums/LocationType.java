package com.andrevalvassori.segnum2020.Model.Enums;

public enum LocationType {

    EVENT(1, "EVENT"),
    HOME(2, "HOME"),
    WORK(3, "WORK"),
    STUDY(4, "STUDY"),
    WORKOUT(5, "WORKOUT");

    public int code;
    private String description;

    private LocationType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static LocationType toEnum(int code) {

        if(code == 0) {
            return null;
        }

        for(LocationType x : LocationType.values()) {
            if(code == x.getCode()) {
                return x;
            }
        }
        throw new IllegalArgumentException("Illegal Code: " + code);
    }
}