package com.leti.social_net.commands.impl;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.Receiver;
import com.leti.social_net.models.Message;
import com.leti.social_net.models.Token;
import com.leti.social_net.models.User;
import com.leti.social_net.models.services.NetworkService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 * Get message from friends
 */
public class GetAllMessageFromFriendsCommad implements Command {

    private static final Logger logger = Logger.getLogger(GetMyFriendsCommand.class);

    private final Receiver receiver;
    private final String token;
    private List<Message> messages;

    public GetAllMessageFromFriendsCommad(Receiver receiver, String token) {
        this.receiver = receiver;
        this.token = token;
    }

    @Override
    public void execute() {
        logger.info("Execute command get messages from friends");
        NetworkService network = receiver.getNetwork();
        Integer id = Token.getIdFromToken(token);
        List<User> userFriends = network.getUserFriends(token, network.getFriendsCount(token), 0);
        if (userFriends != null && userFriends.size() > 0) {
            for (int i = 0; i < userFriends.size(); i++) {
                messages.addAll(network.getMessgaes(userFriends.get(i).getId(), id));
            }
        }
        logger.info("Receive " + (messages == null ? 0 : messages.size()) + " message from friends");
    }
}