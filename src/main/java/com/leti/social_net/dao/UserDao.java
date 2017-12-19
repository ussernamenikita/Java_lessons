package com.leti.social_net.dao;

import com.leti.social_net.models.User;

import java.util.List;

/**
 * Dao for
 * {@link com.leti.social_net.models.User}
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

    /**
     * Get user with username and password
     * @param login username
     * @param password password
     * @return user if exists,or null if not exists
     */
    User getUserByLoginAndPassword(String login,String password);


    /**
     * Get user with such username
     * @param username username
     * @return user if exists, null otherwise
     */
    User getUserByLogin(String username);
}
