package com.andrevalvassori.segnum2020.DTO.location;

import com.andrevalvassori.segnum2020.Model.Enums.LocationType;

public class LocationTypeDTO {

    private int id;
    private String name;

    public LocationTypeDTO() {
    }

    public LocationTypeDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public LocationTypeDTO(LocationType type) {
        this.id = type.getCode();
        this.name = type.name();
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

    @Override
    public String toString() {
        return "LocationTypeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
