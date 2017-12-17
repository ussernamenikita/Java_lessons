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

/**
 * Send message to particular user
 */
@Service
public class SendMessageToParticularUserCommand implements Command {
    private static final Logger logger = Logger.getLogger(SendMessageToParticularUserCommand.class);

    private final Receiver receiver;
    private final MessagesDao messagesDao;
    private String message;

    @Autowired
    public SendMessageToParticularUserCommand(Receiver receiver, MessagesDao messagesDao) {
        this.receiver = receiver;
        this.messagesDao = messagesDao;
    }

    @Override
    public void execute() throws NotAuthorized {
        logger.info("Execute send to particular user");
        NetworkService network = receiver.getNetwork();
        String token = receiver.getToken();
        if(token == null)
        {
            throw new NotAuthorized("This operation requires authorization");
        }
        Integer userId = Token.getIdFromToken(token,network);
        System.out.printf("Enter friend id");
        Integer friendId = receiver.getScanner().nextInt();
        if (!receiver.getNetwork().isFriends(userId,friendId)) {
            logger.error("Users are not friends");
            return;
        }
        System.out.println("Enter message");
        message = receiver.getScanner().next();
        Message msg = new Message();
        msg.setMessage(message);
        //Time in seconds
        msg.setSendTimestamp(System.currentTimeMillis() / 1000);
        User u = receiver.getNetwork().getUser(userId);
        msg.setUseridfrom(u);
        System.out.println("Enter user id to send");
        User u2 = receiver.getNetwork().getUser(friendId);
        msg.setUseridto(u2);
        network.sendMessage(msg);
        messagesDao.putMessage(msg);
        logger.info("Message \"" + message + "\" sent to user with id="+friendId);
    }

    @Override
    public String getName() {
        return "Send message to particular user";
    }
}
