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
 * add user id and send message to him
 */
@Service
public class SendMessageToParticularUserCommand implements Command {
    /**
     * Standrd logger
     */
    private static final Logger logger = Logger.getLogger(SendMessageToParticularUserCommand.class);

    /**
     * Receiver instance
     */
    private final Receiver receiver;

    /**
     * Message which will be send
     */
    private String message;

    @Autowired
    public SendMessageToParticularUserCommand(Receiver receiver) {
        this.receiver = receiver;
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
        message = receiver.getScanner().nextLine();
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
        logger.info("Message \"" + message + "\" sent to user with id="+friendId);
    }

    @Override
    public String getName() {
        return "Send message to particular user";
    }
}
