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

import java.util.ArrayList;
import java.util.List;


/**
 * Get message from friends.
 * Output messages to console
 */
@Service
public class GetAllMessageFromFriendsCommand implements Command {

    /**
     * Standard logger
     */
    private static final Logger logger = Logger.getLogger(GetMyFriendsCommand.class);

    /**
     * Receiver reference
     */
    private final Receiver receiver;

    /**
     * Received messages
     */
    private List<Message> messages = new ArrayList<>();

    @Autowired
    public GetAllMessageFromFriendsCommand(Receiver receiver) {
        this.receiver = receiver;

    }

    @Override
    public void execute() throws NotAuthorized {
        logger.info("Execute command get messages from friends");
        String token = receiver.getToken();
        if(token == null)
        {
            throw new NotAuthorized("This operation requires authorization");
        }
        NetworkService network = receiver.getNetwork();
        User user = receiver.getNetwork().getUser(Token.getIdFromToken(token,receiver.getNetwork()));
        List<User> userFriends = network.getUserFriends(token, network.getFriendsCount(token), 0);
        if (userFriends != null && userFriends.size() > 0) {
            for (int i = 0; i < userFriends.size(); i++) {
                List<Message> msg = network.getMessgaes(userFriends.get(i), user);
                if (msg != null) {
                    messages.addAll(msg);
                }
            }
        }
        messages.forEach(System.out::println);
        logger.info("Receive " + (messages == null ? 0 : messages.size()) + " message from friends");
    }

    @Override
    public String getName() {
        return "Get all messages from friends";
    }
}