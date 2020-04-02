package com.andrevalvassori.segnum2020.DTO.location;

import com.andrevalvassori.segnum2020.Model.Location;

public class LocationSimplifyDTO {

    private int id;
    private String name;
    private String lx;
    private String ly;
    private LocationTypeDTO type;

    public LocationSimplifyDTO() {
    }

    public LocationSimplifyDTO(int id, String name, String lx, String ly, LocationTypeDTO type) {
        this.id = id;
        this.name = name;
        this.lx = lx;
        this.ly = ly;
        this.type = type;
    }

    public LocationSimplifyDTO(Location obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.lx = obj.getLx();
        this.ly = obj.getLy();
        if (obj.getType() != null) this.type = new LocationTypeDTO(obj.getType());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public String getLy() {
        return ly;
    }

    public void setLy(String ly) {
        this.ly = ly;
    }

    public LocationTypeDTO getType() {
        return type;
    }

    public void setType(LocationTypeDTO type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "LocationSimplifyDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lx='" + lx + '\'' +
                ", ly='" + ly + '\'' +
                ", type=" + type.toString() +
                '}';
    }
}
