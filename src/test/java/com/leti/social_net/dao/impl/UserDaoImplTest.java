package com.leti.social_net.dao.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
    UserDaoImpl userDao = new UserDaoImpl();
    ArrayList<User> users = null;

    public void setUp() throws Exception {
        super.setUp();
        Gson gson = new Gson();
        ClassLoader loader = getClass().getClassLoader();
        Type usersType = new TypeToken<List<User>>() {
        }.getType();
        users = gson.fromJson(new InputStreamReader(loader.getResourceAsStream("users.json")), usersType);
    }

    public void testGetAllUser() throws Exception {
        List<User> insertedUser = userDao.getAllUser();
        assertEquals(insertedUser.size(),users.size());
    }

    public void testGetParticularUser() throws Exception {
        User u1 = users.get(0);
        assertTrue(u1.equals(userDao.getParticularUser(u1.getId())));
    }

    public void testInsertOrUpdate() throws Exception {
        userDao.insertOrUpdate(users);

    }



    public void testCreateIfNotExistsTable() throws Exception {
        userDao.createIfNotExistsTable();
    }

}