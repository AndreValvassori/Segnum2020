package com.andrevalvassori.segnum2020.DTO.location;

public class LocationNewDTO {

    private int id;
    private String name;
    private String lx;
    private String ly;
    private int type;

    public LocationNewDTO() {
    }

    public LocationNewDTO(int id, String name, String lx, String ly, int type) {
        this.id = id;
        this.name = name;
        this.lx = lx;
        this.ly = ly;
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
