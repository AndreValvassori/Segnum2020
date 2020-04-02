package com.andrevalvassori.segnum2020.Model.Enums;

public enum EventSource {

    USER(1, "FROM_USER"),
    CRAWLER(2, "FROM_CRAWLER"),
    API(3, "FROM_API");

    public int code;
    private String description;

    private EventSource(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static EventSource toEnum(int code) {

        if(code == 0) {
            return null;
        }

        for(EventSource x : EventSource.values()) {
            if(code == x.getCode()) {
                return x;
            }
        }
        throw new IllegalArgumentException("Illegal Code: " + code);
    }
}