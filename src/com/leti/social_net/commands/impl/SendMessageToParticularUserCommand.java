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
 * Created by Nikita on 21.11.2017.
 */
public class SendMessageToParticularUserCommand implements Command {
    private static final Logger logger = Logger.getLogger(SendMessageToParticularUserCommand.class);

    private final Receiver receiver;
    private final DatabaseService messagesDao;
    private final String message;
    private final String token;
    private final Integer userTo;

    public SendMessageToParticularUserCommand(Receiver receiver, DatabaseService messagesDao, String message, String token, Integer userTo) {
        this.receiver = receiver;
        this.messagesDao = messagesDao;
        this.message = message;
        this.token = token;
        this.userTo = userTo;
    }

    @Override
    public void execute() {
        logger.info("Execute send to particular user");
        NetworkService network = receiver.getNetwork();
        Integer userId = Token.getIdFromToken(token);
        if (userId == null) {
            logger.error("Invalid token,can't send message");
            return;
        }
        Message msg = new Message();
        msg.setMessage(message);
        //Time in seconds
        msg.setSendTimestamp(System.currentTimeMillis() / 1000);
        msg.setUserIdFrom(userId);
        msg.setUserIdTo(userTo);
        network.sendMessage(msg);
        //TODO messagesDao.addTosent
        logger.info("Message \"" + message + "\" sent to user with id="+userTo);
    }
}
