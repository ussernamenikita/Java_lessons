package com.leti.social_net.dao.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.leti.social_net.dao.UserDao;
import com.leti.social_net.models.User;
import junit.framework.TestCase;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * User dao tests
 */
public class UserDaoImplTest extends TestCase {
    UserDao userDao = new UserDaoJpaImpl();
    ArrayList<User> users = null;

    public void setUp() throws Exception {
        super.setUp();
        }


    public void testGetParticularUser() throws Exception {
        User user =  new User();
        user.setName("name");
        user.setSurname("surname");
        int newId = userDao.insertOrUpdate(user);
        User newUser = userDao.getParticularUser(newId);
        assertTrue(newUser != null);
        assertTrue(newId == newUser.getId());
    }

    public void testInsertOrUpdate() throws Exception {
        userDao.insertOrUpdate(users);

    }




}