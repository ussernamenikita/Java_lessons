package com.leti.social_net.models;

import javax.persistence.*;

/**
 * User model class
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    //@GeneratedValue(strategy = GenerationType.TABLE ,generator = "SEQ_USER_ID_GEN")
    @GeneratedValue(strategy = GenerationType.TABLE ,generator = "SEQ_USER_ID_GEN")
    @SequenceGenerator(name = "SEQ_USER_ID_GEN",sequenceName = "users_id_seq")
    private Integer id;

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

    private String username;

    private String password;


    public User() {
    }

    public User(Integer id,String name,String surname,String registered,String email,
                String address, String phone, String birthday, String company, Boolean online, String avatar ) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * Update all fields
     * this{@link #id} and newUserData.id must be equals.
     * @param newUserData object with new data
     */
    public void update(User newUserData) {
        if(id.equals(newUserData.id)) {
            this.registered = newUserData.registered;
            this.address = newUserData.address;
            this.phone = newUserData.phone;
            this.email = newUserData.email;
            this.birthday = newUserData.birthday;
            this.company = newUserData.company;
            this.online = newUserData.online;
            this.avatar = newUserData.avatar;
            this.surname = newUserData.surname;
            this.name = newUserData.name;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "registered='" + registered + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", birthday='" + birthday + '\'' +
                ", company='" + company + '\'' +
                ", online=" + online +
                ", avatar='" + avatar + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
