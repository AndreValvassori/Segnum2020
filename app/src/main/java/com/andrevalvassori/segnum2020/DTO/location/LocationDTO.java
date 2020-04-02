package com.andrevalvassori.segnum2020.DTO.location;


import com.andrevalvassori.segnum2020.DTO.event.EventSimplifyDTO;
import com.andrevalvassori.segnum2020.DTO.user.UserSimplifyDTO;
import com.andrevalvassori.segnum2020.Model.Event;
import com.andrevalvassori.segnum2020.Model.Location;
import com.andrevalvassori.segnum2020.Model.User;

public class LocationDTO {

    private int id;
    private String name;
    private String lx;
    private String ly;
    private LocationTypeDTO type;
    private UserSimplifyDTO user;
    private EventSimplifyDTO event;

    public LocationDTO() {
    }

    public LocationDTO(int id, String name, String lx, String ly, LocationTypeDTO type, UserSimplifyDTO user) {
        this.id = id;
        this.name = name;
        this.lx = lx;
        this.ly = ly;
        this.type = type;
        this.user = user;
    }

    public LocationDTO(int id, String name, String lx, String ly, LocationTypeDTO type, EventSimplifyDTO event) {
        this.id = id;
        this.name = name;
        this.lx = lx;
        this.ly = ly;
        this.type = type;
        this.event = event;
    }

    public LocationDTO(int id, String name, String lx, String ly, LocationTypeDTO type, User user) {
        this.id = id;
        this.name = name;
        this.lx = lx;
        this.ly = ly;
        this.type = type;
        if (user != null) this.user = new UserSimplifyDTO(user);
    }

    public LocationDTO(int id, String name, String lx, String ly, LocationTypeDTO type, Event event) {
        this.id = id;
        this.name = name;
        this.lx = lx;
        this.ly = ly;
        this.type = type;
        if (event != null) this.event = new EventSimplifyDTO(event);
    }

    public LocationDTO(Location obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.lx = obj.getLx();
        this.ly = obj.getLy();
        if (obj.getType() != null) this.type = new LocationTypeDTO(obj.getType());
        if (obj.getEvent() != null) this.event = new EventSimplifyDTO(obj.getEvent());
        if (obj.getUser() != null) this.user = new UserSimplifyDTO(obj.getUser());
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

    public UserSimplifyDTO getUser() {
        return user;
    }

    public void setUser(UserSimplifyDTO user) {
        this.user = user;
    }

    public EventSimplifyDTO getEvent() {
        return event;
    }

    public void setEvent(EventSimplifyDTO event) {
        this.event = event;
    }


}
