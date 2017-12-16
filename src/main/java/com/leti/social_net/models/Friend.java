package com.leti.social_net.models;

import javax.persistence.*;


@Entity
public class Friend {

    @Id
    @GeneratedValue
    int id;


    @ManyToOne
    User u1;


    @ManyToOne
    User u2;

    public User getU1() {
        return u1;
    }

    public void setU1(User u1) {
        this.u1 = u1;
    }

    public User getU2() {
        return u2;
    }

    public void setU2(User u2) {
        this.u2 = u2;
    }
}
