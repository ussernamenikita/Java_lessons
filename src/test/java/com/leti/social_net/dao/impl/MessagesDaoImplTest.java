package com.leti.social_net.dao.impl;

import com.leti.social_net.dao.MessagesDao;
import com.leti.social_net.models.Message;
import com.leti.social_net.models.User;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikita on 29.11.2017.
 */
public class MessagesDaoImplTest extends TestCase {
    MessagesDao dao = new MessagesDaoJpaImpl();

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Message msg = new Message();
        User testUser1 = new User();
        testUser1.setName("test1");
        User testUser2 = new User();
        testUser2.setName("test2");
        msg.setUserIdTo(testUser2);
        msg.setUserIdFrom(testUser1);
        msg.setMessage("TestMsg");
        msg.setSendTimestamp(System.currentTimeMillis());
        dao.putMessage(msg);
    }

    public void testGetMessages() throws Exception {
        List<Message>  messagesFromDb = dao.getMessages(1,2);
        assertTrue(messagesFromDb.size() > 0);
        messagesFromDb.forEach(message -> {
            assertTrue(message.getUserIdFrom().getId().equals(1)&&message.getUserIdTo().getId().equals(2));
        });
    }

    public void testPutMessage() throws Exception {
        List<Message> localMsgs = dao.getMessages(1,2);
        dao.putMessage(localMsgs.get(0));
        assertTrue(localMsgs.size() == (dao.getMessages(1,2).size()-1));
    }

}