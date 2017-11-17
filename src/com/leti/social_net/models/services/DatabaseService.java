package com.leti.social_net.models.services;

import com.leti.social_net.models.User;
import com.sun.istack.internal.NotNull;

import java.util.List;

/**
 * Local database service.
 */
public interface DatabaseService {

    //TODO write methods

    public void saveUsersInfo(@NotNull List<User> users);

    public void saveUsersInfo(@NotNull User user);

    public User getUserById(int id);



}
