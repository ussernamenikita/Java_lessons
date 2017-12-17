package com.leti.social_net.dao.impl;

import com.leti.social_net.dao.UserDao;
import com.leti.social_net.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Nikita on 12.12.2017.
 */
@Repository
@Transactional
public class UserDaoJpaImpl implements UserDao{

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<User> getAllUser() {
        return entityManager.createQuery("select u from User u",User.class).getResultList();
    }

    @Override
    public User getParticularUser(Integer id) {
        return entityManager.find(User.class,id);
    }

    @Override
    public void insertOrUpdate(List<User> user) {
        entityManager.getTransaction();
        user.forEach(entityManager::merge);
        entityManager.getTransaction().commit();
    }

    @Override
    public int insertOrUpdate(User user) {
        return entityManager.merge(user).getId();
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        List<User> list  =  entityManager.createQuery("from User u where u.username = ? and u.password = ?",User.class)
                .setParameter(1,login)
                .setParameter(2,password).getResultList();
        if(list == null || list.size() < 1)
            return null;
        return list.get(0);
    }

    @Override
    public User getUserByLogin(String username) {
        List<User> users = entityManager
                .createQuery("from User u where u.username = ?",User.class)
                .setParameter(1,username).getResultList();
        if(users == null || users.size() < 1)
            return null;
        else
            return users.get(0);
    }
}
