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
 * Send message to particular user
 */
public class SendMessageToParticularUserCommand implements Command {
    private static final Logger logger = Logger.getLogger(SendMessageToParticularUserCommand.class);

    private final Receiver receiver;
    private final DatabaseService messagesDao;
    private String message;
    private String token;
    private Integer userTo;

    public SendMessageToParticularUserCommand(Receiver receiver, DatabaseService messagesDao) {
        this.receiver = receiver;
        this.messagesDao = messagesDao;
    }

    @Override
    public void execute() {
        logger.info("Execute send to particular user");
        NetworkService network = receiver.getNetwork();
        System.out.println("Enter yout token");
        token = receiver.getScanner().next();
        Integer userId = Token.getIdFromToken(token);
        if (userId == null) {
            logger.error("Invalid token,can't send message");
            return;
        }
        System.out.println("Enter message");
        message = receiver.getScanner().next();
        Message msg = new Message();
        msg.setMessage(message);
        //Time in seconds
        msg.setSendTimestamp(System.currentTimeMillis() / 1000);
        msg.setUserIdFrom(userId);
        System.out.println("Enter user id to send");
        msg.setUserIdTo(userTo);
        network.sendMessage(msg);
        logger.info("Message \"" + message + "\" sent to user with id="+userTo);
    }

    @Override
    public String getName() {
        return "Send message to particular user";
    }
}
