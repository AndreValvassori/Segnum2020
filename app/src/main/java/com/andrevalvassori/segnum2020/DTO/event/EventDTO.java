package com.andrevalvassori.segnum2020.DTO.event;


import com.andrevalvassori.segnum2020.DTO.eventType.EventTypeSimplifyDTO;
import com.andrevalvassori.segnum2020.DTO.location.LocationSimplifyDTO;
import com.andrevalvassori.segnum2020.DTO.user.UserSimplifyDTO;
import com.andrevalvassori.segnum2020.Model.Event;
import com.andrevalvassori.segnum2020.Model.User;

import java.sql.Date;

public class EventDTO {

    private int id;
    private String name;
    private String description;
    private UserSimplifyDTO user;
    private EventTypeSimplifyDTO eventTypeDTO;
    private LocationSimplifyDTO locationDTO;
    private String Created_at;

    public EventDTO() {
    }

    public EventDTO(int id, String name, String description, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user = new UserSimplifyDTO(user);
    }

    public EventDTO(int id, String name, String description, UserSimplifyDTO user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user = user;
    }

    public EventDTO(Event event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        if (event.getUser() != null) this.user = new UserSimplifyDTO(event.getUser());
        if (event.getLocation() != null) this.locationDTO = new LocationSimplifyDTO(event.getLocation());
        if (event.getEventType() != null) this.eventTypeDTO = new EventTypeSimplifyDTO(event.getEventType());
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserSimplifyDTO getUser() {
        return user;
    }

    public void setUser(UserSimplifyDTO user) {
        this.user = user;
    }

    public LocationSimplifyDTO getLocationDTO() {
        return locationDTO;
    }

    public void setLocationDTO(LocationSimplifyDTO locationDTO) {
        this.locationDTO = locationDTO;
    }

    public EventTypeSimplifyDTO getEventTypeDTO() {
        return eventTypeDTO;
    }

    public void setEventTypeDTO(EventTypeSimplifyDTO eventTypeDTO) {
        this.eventTypeDTO = eventTypeDTO;
    }

    public String getCreated_at() {
        return Created_at;
    }

    public void setCreated_at(String created_at) {
        Created_at = created_at;
    }

    @Override
    public String toString() {
        return "EventDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", eventTypeDTO=" + eventTypeDTO.toString() +
//                ", locationDTO=" + locationDTO.toString() +
                '}';
    }
}
