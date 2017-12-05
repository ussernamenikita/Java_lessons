package com.leti.social_net.commands.impl;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.Receiver;
import com.leti.social_net.dao.MessagesDao;
import com.leti.social_net.models.Message;
import com.leti.social_net.models.Token;
import com.leti.social_net.models.User;
import com.leti.social_net.services.DatabaseService;
import com.leti.social_net.services.NetworkService;
import org.apache.log4j.Logger;

import java.util.List;


/**
 * Send message to all friends command
 */
public class SendMessageToAllFriendsCommand implements Command {
    private static final Logger logger = Logger.getLogger(SendMessageToAllFriendsCommand.class);

    private final Receiver receiver ;
    private final MessagesDao messagesDao;


    public SendMessageToAllFriendsCommand(Receiver receiver, MessagesDao messagesDao) {
        this.receiver = receiver;
        this.messagesDao = messagesDao;

    }

    @Override
    public void execute() {
        logger.info("Execute send to all friends");
        String message = null;
        NetworkService network = receiver.getNetwork();
        System.out.println("Enter your token");
        String token = receiver.getScanner().next();
        int count = network.getFriendsCount(token);
        if(count > 0)
        {
            System.out.println("Enter your message");
            message = receiver.getScanner().next();
            final String localMsg = message;
            List<User> friends = network.getUserFriends(token,count,0);
            friends.forEach(user -> {
                Message msg = new Message();
                msg.setMessage(localMsg);
                //Time in seconds
                msg.setSendTimestamp(System.currentTimeMillis()/1000);
                msg.setUserIdFrom(Token.getIdFromToken(token));
                msg.setUserIdTo(user.getId());
                //Save message to database
                messagesDao.putMessage(msg);
                network.sendMessage(msg);
            });
        };
        logger.info("Message \""+message+"\" send to "+count+" users");
    }

    @Override
    public String getName() {
        return "Get message to all friends";
    }

}
