package com.leti.social_net.dao.impl;

import com.leti.social_net.dao.FriendsDao;
import com.leti.social_net.models.Friend;
import com.leti.social_net.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Friends DAO implementation
 * ith JPA and Hibernate
 */
@Transactional
@Repository
public class FriendsDaoJpaImp implements FriendsDao {


    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<User> getFriends(User u) {
        List<Friend> list = entityManager
                .createQuery("select f from Friend as f where f.u1 = ? or f.u2 = ?",Friend.class)
                .setParameter(1,u)
                .setParameter(2,u)
                .getResultList();
        if(list == null)
            return new ArrayList<>();
        else
            return list
                    .stream()
                    .flatMap(friend -> Stream.of(friend.getU1(),friend.getU2()))
                    .distinct()
                    .filter(user -> !user.equals(u))
                    .collect(Collectors.toList());
    }

    @Override
    public void addFriends(User u1, User u2) {
        Friend f = new Friend();
        f.setU1(u1);
        f.setU2(u2);
        entityManager.merge(f);
    }

    @Override
    public List<User> getFriends(User user, int limit, int offset) {
        List<Friend> list = entityManager
                .createQuery("select f from Friend as f where f.u1 = ? or f.u2 = ?",Friend.class)
                .setParameter(1,user)
                .setParameter(2,user)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
        if(list == null)
            return new ArrayList<>();
        else
            return list
                    .stream()
                    .flatMap(friend -> Stream.of(friend.getU1(),friend.getU2()))
                    .distinct()
                    .filter(user1 -> !user1.equals(user))
                    .collect(Collectors.toList());
    }
}
