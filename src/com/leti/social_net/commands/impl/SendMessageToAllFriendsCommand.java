package com.leti.social_net.commands.impl;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.Receiver;
import com.leti.social_net.models.Message;
import com.leti.social_net.models.Token;
import com.leti.social_net.models.User;
import com.leti.social_net.models.services.DatabaseService;
import com.leti.social_net.models.services.NetworkService;
import org.apache.log4j.Logger;

import java.util.List;


/**
 * Send message to all friends command
 */
public class SendMessageToAllFriendsCommand implements Command {
    private static final Logger logger = Logger.getLogger(SendMessageToAllFriendsCommand.class);

    private final Receiver receiver ;
    private final DatabaseService messagesDao;
    private final String message;
    private final String token;

    public SendMessageToAllFriendsCommand(Receiver receiver, DatabaseService messagesDao, String message, String token) {
        this.receiver = receiver;
        this.messagesDao = messagesDao;
        this.message = message;
        this.token = token;
    }

    @Override
    public void execute() {
        logger.info("Execute send to all friends");
        NetworkService network = receiver.getNetwork();
        int count = network.getFriendsCount(token);
        if(count > 0)
        {
            List<User> friends = network.getUserFriends(token,count,0);
            friends.forEach(user -> {
                Message msg = new Message();
                msg.setMessage(message);
                //Time in seconds
                msg.setSendTimestamp(System.currentTimeMillis()/1000);
                msg.setUserIdFrom(Token.getIdFromToken(token));
                msg.setUserIdTo(user.getId());
                network.sendMessage(msg);
            });
        };
        //TODO messagesDao.addTosent
        logger.info("Message \""+message+"\" send to "+count+" users");
    }

}