package com.leti.social_net.commands.impl;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.NotAuthorized;
import com.leti.social_net.commands.Receiver;
import com.leti.social_net.dao.MessagesDao;
import com.leti.social_net.models.Message;
import com.leti.social_net.models.Token;
import com.leti.social_net.models.User;
import com.leti.social_net.services.NetworkService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Send message to all friends command
 */
@Service
public class SendMessageToAllFriendsCommand implements Command {
    private static final Logger logger = Logger.getLogger(SendMessageToAllFriendsCommand.class);

    private final Receiver receiver ;


    @Autowired
    public SendMessageToAllFriendsCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() throws NotAuthorized {
        logger.info("Execute send to all friends");
        String message = null;
        NetworkService network = receiver.getNetwork();
        String token = receiver.getToken();
        if(token == null)
        {
            throw new NotAuthorized("This operation requires authorization");
        }
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
                User u = new User();
                u.setId(Token.getIdFromToken(token,network));
                msg.setUseridfrom(u);
                msg.setUseridto(user);
                //Save message to database
                network.sendMessage(msg);
            });
        };
        logger.info("Message \""+message+"\" send to "+count+" users");
    }

    @Override
    public String getName() {
        return "Send message to all friends";
    }

}
