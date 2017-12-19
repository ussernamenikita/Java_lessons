package com.leti.social_net.dao.impl;

import com.leti.social_net.config.Configuration;
import com.leti.social_net.dao.MessagesDao;
import com.leti.social_net.dao.UserDao;
import com.leti.social_net.models.Message;
import com.leti.social_net.models.User;
import junit.framework.TestCase;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Tests for messages
 */
public class MessagesDaoImplTest extends TestCase {


    User testUser1 = new User();
    User testUser2 = new User();

    MessagesDao msgDao;
    UserDao userDao;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Configuration.class);
        msgDao = (MessagesDao) applicationContext.getBean("messagesDaoJpaImpl");
        userDao = (UserDao) applicationContext.getBean("userDaoJpaImpl");
        Message msg = new Message();
        testUser1.setUsername("test1");
        testUser1.setPassword("pass1");
        testUser2.setUsername("test2");
        testUser2.setPassword("pass2");
        userDao.insertOrUpdate(testUser1);
        userDao.insertOrUpdate(testUser2);
        testUser1 = userDao.getUserByLoginAndPassword("test1","pass1");
        testUser2 = userDao.getUserByLoginAndPassword("test2","pass2");
        msg.setUseridto(testUser2);
        msg.setUseridfrom(testUser1);
        msg.setMessage("TestMsg");
        msg.setSendTimestamp(System.currentTimeMillis()/1000);
        msgDao.putMessage(msg);
    }

    public void testGetMessages() throws Exception {
        List<Message>  messagesFromDb = msgDao.getMessages(testUser1,testUser2);
        assertTrue(messagesFromDb.size() > 0);
        messagesFromDb.forEach(message -> {
            assertTrue(message.getUseridfrom().getId().equals(testUser1.getId())&&message.getUseridto().getId().equals(testUser2.getId()));
        });
    }


}