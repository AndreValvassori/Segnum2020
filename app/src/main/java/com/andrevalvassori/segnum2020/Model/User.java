package com.andrevalvassori.segnum2020.Model;


import com.andrevalvassori.segnum2020.Model.Enums.Profile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String email;
    private String phone;
    private Date birthday;
    private String password;

    private Set<Integer> profiles = new HashSet<>();

    private List<Location> myLocations = new ArrayList<>();

    private List<Vote> myVotes = new ArrayList<>();

    private List<Vote> myComments = new ArrayList<>();

    private List<Event> myEvents = new ArrayList<>();

    public User() {
        addPerfil(Profile.USER);
    }

    public User(int id, String name, String email, String phone, Date birthday, String password) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.password = password;
        addPerfil(Profile.USER);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Location> getMyLocations() {
        return myLocations;
    }

    public void setMyLocations(List<Location> myLocations) {
        this.myLocations = myLocations;
    }

    public Set<Profile> getProfiles() {
        return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Profile profile) {
        profiles.add(profile.getCode());
    }

    public void setProfiles(Set<Integer> profiles) {
        this.profiles = profiles;
    }

    public List<Vote> getMyVotes() {
        return myVotes;
    }

    public void setMyVotes(List<Vote> myVotes) {
        this.myVotes = myVotes;
    }

    public List<Vote> getMyComments() {
        return myComments;
    }

    public void setMyComments(List<Vote> myComments) {
        this.myComments = myComments;
    }

    public List<Event> getMyEvents() {
        return myEvents;
    }

    public void setMyEvents(List<Event> myEvents) {
        this.myEvents = myEvents;
    }
}
