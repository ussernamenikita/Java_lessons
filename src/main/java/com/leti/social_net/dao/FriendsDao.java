package com.leti.social_net.dao;

import com.leti.social_net.models.User;

import java.util.List;


/**
 * DAO for friends
 * ...
 */
public interface FriendsDao {

    /**
     * Get all friends for this user
     * @param u user which friends need
     * @return user friends
     */
    List<User> getFriends(User u);

    /**
     * Add some uiser to friend
     * u1 will e friend for u2,
     * and u2 will be friend for u1
     * @param u1 friend1
     * @param u2 friend2
     */
    void addFriends(User u1, User u2);

    /**
     * Get friends with limit and offset
     * @param user which friends need
     * @param limit limit
     * @param offset offset
     * @return list with friends
     */
    List<User> getFriends(User user,int limit,int offset);

}
