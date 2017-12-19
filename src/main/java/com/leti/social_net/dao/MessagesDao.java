package com.leti.social_net.dao;

import com.leti.social_net.models.Message;
import com.leti.social_net.models.User;

import java.util.List;

/**
 * DAO for messages
 * ...
 */
public interface MessagesDao{

    /**
     *  Get all messages from user with id userFromId to user with id userToid
     * @param userFromId user from
     * @param userToid  user to
     * @return list with messages from user userFromId to user userToid
     */
     List<Message> getMessages(User userFromId, User userToid);


    /**
     * Put some message to database
     * Messages don't updated only insert
     * @param msg message
     */
    void putMessage(Message msg);
}
