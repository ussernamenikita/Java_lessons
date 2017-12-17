package com.leti.social_net.dao.impl;

import com.leti.social_net.dao.MessagesDao;
import com.leti.social_net.models.Message;
import com.leti.social_net.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Nikita on 12.12.2017.
 */
@Transactional
@Repository
public class MessagesDaoJpaImpl implements MessagesDao {


    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Message> getMessages(User userFromId,User userToid) {
        return entityManager
                .createQuery("from Message m where m.useridfrom = ? and m.useridto = ?",Message.class)
                .setParameter(1,userFromId)
                .setParameter(2,userToid).getResultList();
    }

    @Override
    public void putMessage(Message msg) {
        entityManager.merge(msg);
    }
}
