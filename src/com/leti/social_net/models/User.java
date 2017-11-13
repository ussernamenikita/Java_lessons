package com.leti.social_net.models;

/**
 * User model class
 */
public class User {
    private String registered;
    private String address;
    private String phone;
    private String email;
    private String birthday;
    private String company;
    private Boolean online;
    private String avatar;
    private String surname;
    private String name;
    private Integer id;

    public User() {
    }

    public User(String registered, String address, String phone, String email, String birthday, String company, Boolean online, String avatar, String surname, String name, Integer id) {
        this.registered = registered;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
        this.company = company;
        this.online = online;
        this.avatar = avatar;
        this.surname = surname;
        this.name = name;
        this.id = id;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
