package com.leti.social_net.dao.impl;

import com.leti.social_net.dao.UserDao;
import com.leti.social_net.models.User;
import com.leti.social_net.services.servicesImpl.DatabaseServiceImplementation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;


@RunWith(MockitoJUnitRunner.class)
public class MockTestClass {

    private DatabaseServiceImplementation databaseServiceImplementation = null;

    private User userInstance= new User(1,
            null,null,null,
            null,null,null,
            null,null,null,null);

    @Mock
    UserDao userDao;


    @Before
    public void setUp() throws Exception {
        databaseServiceImplementation = new DatabaseServiceImplementation(userDao);
        Mockito.when(userDao.getAllUser()).thenReturn(null);
        Mockito.when(userDao.getParticularUser(1))
                .thenReturn(userInstance);
    }


    @Test
    public void verifyUserDaoInsertOrUpdateCalledWithUser()
    {
        databaseServiceImplementation.saveUsersInfo(userInstance);
        Mockito.verify(userDao).insertOrUpdate(userInstance);
    }


    @Test
    public void verifyUserDaoInsertOrUpdateCalledWithList()
    {
        ArrayList<User> tmpList = new ArrayList<>(1);
        tmpList.add(userInstance);
        databaseServiceImplementation.saveUsersInfo(tmpList);
        Mockito.verify(userDao).insertOrUpdate(tmpList);
    }

    @Test
    public void justTests()
    {
        Assert.assertEquals(userDao.getAllUser(),null);
        Assert.assertEquals(userDao.getParticularUser(1).getId(),new Integer(1));
    }

    @Test
    public void verifyInserNullUser()
    {
        Assert.assertEquals(databaseServiceImplementation.getUserById(-1),null);
        Assert.assertEquals(databaseServiceImplementation.getUserById(1),userInstance);
    }
}
