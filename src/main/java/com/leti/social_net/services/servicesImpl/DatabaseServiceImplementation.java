package com.leti.social_net.services.servicesImpl;

import com.leti.social_net.dao.UserDao;
import com.leti.social_net.models.User;
import com.leti.social_net.services.DatabaseService;

import java.util.List;
import java.util.Objects;

/**
 * Created by Nikita on 05.12.2017.
 */
public class DatabaseServiceImplementation implements DatabaseService {

    private UserDao userDao;

    public DatabaseServiceImplementation(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public boolean saveUsersInfo(List<User> users) {
        if(users == null || users.size() == 0)
            return false;
        else
           userDao.insertOrUpdate(users);
        return true;
    }

    @Override
    public boolean saveUsersInfo(User user) {
        if(user == null || user.getId() == null || user.getId() < 0)
            return false;
        userDao.insertOrUpdate(user);
        return true;
    }

    @Override
    public User getUserById(int id) {
        if(id < 0)
            return null;
        return userDao.getParticularUser(id);
    }
}
