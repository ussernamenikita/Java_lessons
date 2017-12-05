package com.leti.social_net.dao.impl;

import com.leti.social_net.dao.MessagesDao;
import com.leti.social_net.models.Message;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikita on 29.11.2017.
 */
public class MessagesDaoImplTest extends TestCase {
    MessagesDao dao = new MessagesDaoImpl();

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Message msg = new Message();
        msg.setUserIdTo(2);
        msg.setUserIdFrom(1);
        msg.setMessage("TestMsg");
        msg.setSendTimestamp(System.currentTimeMillis());
        dao.putMessage(msg);
    }

    public void testGetMessages() throws Exception {
        List<Message>  messagesFromDb = dao.getMessages(1,2);
        assertTrue(messagesFromDb.size() > 0);
        messagesFromDb.forEach(message -> {
            assertTrue(message.getUserIdFrom().equals(1)&&message.getUserIdTo().equals(2));
        });
    }

    public void testPutMessage() throws Exception {
        List<Message> localMsgs = dao.getMessages(1,2);
        dao.putMessage(localMsgs.get(0));
        assertTrue(localMsgs.size() == (dao.getMessages(1,2).size()-1));

    }

    public void testCreateIfNotExistsTable() throws Exception {
        dao = new MessagesDaoImpl();
    }

}