package com.leti.social_net.models;

import javax.persistence.*;


@Entity
@Table(name = "friends")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "friends_gen")
    @SequenceGenerator(name = "friends_gen",sequenceName = "friends_id_seq1")
    int id;


    @ManyToOne()
    @JoinColumn(name = "u1")
    User u1;


    @ManyToOne
    @JoinColumn(name = "u2")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Friend friend = (Friend) o;

        return id == friend.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
