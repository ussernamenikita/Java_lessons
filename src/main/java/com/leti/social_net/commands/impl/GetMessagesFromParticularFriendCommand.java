package com.leti.social_net.commands.impl;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.NotAuthorized;
import com.leti.social_net.commands.Receiver;
import com.leti.social_net.models.Message;
import com.leti.social_net.models.Token;
import com.leti.social_net.models.User;
import com.leti.social_net.services.NetworkService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Get messages from particular friend.
 * Output messges to console
 */
@Service
public class GetMessagesFromParticularFriendCommand implements Command{

    /**
     * Standard logger
     */
    private static final Logger logger = Logger.getLogger(GetMessagesFromParticularFriendCommand.class);

    /**
     * Receiver reference
     */
    private final Receiver receiver;

    /**
     * Received messages
     */
    private List<Message> messages;

    @Autowired
    public GetMessagesFromParticularFriendCommand(Receiver receiver) {
        this.receiver = receiver;

    }

    @Override
    public void execute() throws NotAuthorized {
        String token = receiver.getToken();
        if(token == null)
        {
            throw new NotAuthorized("This operation requires authorization");
        }
        System.out.printf("Enter friend id");
        Integer friendId = receiver.getScanner().nextInt();
        logger.info("Try to get message from user with token "+token+" and user with id"+friendId);
        Integer id = Token.getIdFromToken(token,receiver.getNetwork());
        User user = receiver.getNetwork().getUser(id);
        User friend = receiver.getNetwork().getUser(friendId);
        if (id == null)
        {
            messages = null;
            logger.warn("Invalid token");
            return;
        }
        NetworkService network = receiver.getNetwork();
        if(!network.isFriends(id,friendId))
        {
            messages = null;
            logger.warn("Invalid argument, this users is not a friends");
            return;
        }
        messages = network.getMessgaes(user,friend);
        logger.info("Received "+messages.size()+" messages");
        messages.forEach(System.out::println);

    }

    @Override
    public String getName() {
        return "Get all messages from particular friend";
    }
}
