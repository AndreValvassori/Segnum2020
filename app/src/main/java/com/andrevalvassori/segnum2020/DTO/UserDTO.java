package com.andrevalvassori.segnum2020.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDTO {

    private int id;
    private String name;
    private String email;
    private String phone;
    private Date birthday;

    private String password;


    private List<ProfileDTO> profiles = new ArrayList<>();

    public UserDTO() {
    }

    public UserDTO(int id, String name, String email, String phone, Date birthday) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
    }
//
//    public UserDTO(int id, String name, String email, String phone, Date birthday/*, List<ProfileDTO> profiles*/) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.phone = phone;
//        this.birthday = birthday;
//        this.profiles = profiles;
//    }

  /*  public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.birthday = user.getBirthday();
        this.profilesEnumToDto(user.getProfiles());
    }
*/
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

    public List<ProfileDTO> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<ProfileDTO> profiles) {
        this.profiles = profiles;
    }
//
//    public void profilesEnumToDto(Set<Profile> enumProfiles) {
//        enumProfiles.forEach(p -> {
//            this.profiles.add(new ProfileDTO(p.getCode(), p.getDescription()));
//        });
//    }


    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", birthday=" + birthday +
                ", profiles=" + profiles +
                '}';
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
