package com.andrevalvassori.segnum2020.DTO.user;

import java.util.Date;

public class UserNewDTO {

    private String name;
    private String email;
    private String phone;
    private Date birthday;
    private String password;

    public UserNewDTO() {
    }

    public UserNewDTO(String name, String email, String phone, Date birthday, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.password = password;
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


}
