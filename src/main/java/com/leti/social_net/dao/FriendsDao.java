package com.leti.social_net.dao;

import com.leti.social_net.models.Friend;
import com.leti.social_net.models.User;

import java.util.List;


public interface FriendsDao {

    List<User> getFriends(User u);

    void addFriends(User u1, User u2);

    List<User> getFriends(User user,int limit,int offset);

}
