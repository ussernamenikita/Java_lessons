package com.leti.social_net.services;

import com.leti.social_net.models.User;
import com.sun.istack.internal.NotNull;

import java.util.List;

/**
 * Service for mock part
 */
public interface DatabaseService {

    public boolean saveUsersInfo(@NotNull List<User> users);

    public boolean saveUsersInfo(@NotNull User user);

    public User getUserById(int id);





}
