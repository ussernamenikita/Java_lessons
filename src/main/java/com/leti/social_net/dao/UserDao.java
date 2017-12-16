package com.leti.social_net.dao;

import com.leti.social_net.models.User;

import java.util.List;

/**
 * Dao for {@link com.leti.social_net.models.User}
 */
public interface UserDao {

    /**
     * Get all user from database
     * @return list of all user from database
     */
    List<User> getAllUser();

    /**
     * Get user from database by id
     * @param id user  id
     * @return User with id
     */
    User getParticularUser(Integer id);

    /**
     * Insert or update users
     * @param user users list
     */
    void insertOrUpdate(List<User> user);

    /**
     * Insert or update one user
     * @param user user which need update or insert
     */
    int insertOrUpdate(User user);

}
