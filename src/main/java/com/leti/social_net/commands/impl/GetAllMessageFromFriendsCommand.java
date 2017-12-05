package com.leti.social_net.commands.impl;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.Receiver;
import com.leti.social_net.models.Message;
import com.leti.social_net.models.Token;
import com.leti.social_net.models.User;
import com.leti.social_net.services.NetworkService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 * Get message from friends
 */
public class GetAllMessageFromFriendsCommand implements Command {

    private static final Logger logger = Logger.getLogger(GetMyFriendsCommand.class);

    private final Receiver receiver;
    private List<Message> messages = new ArrayList<>();

    public GetAllMessageFromFriendsCommand(Receiver receiver) {
        this.receiver = receiver;

    }

    @Override
    public void execute() {
        logger.info("Execute command get messages from friends");
        System.out.println("Enter your token");
        String token = receiver.getScanner().next();
        NetworkService network = receiver.getNetwork();
        Integer id = Token.getIdFromToken(token);
        List<User> userFriends = network.getUserFriends(token, network.getFriendsCount(token), 0);
        if (userFriends != null && userFriends.size() > 0) {
            for (int i = 0; i < userFriends.size(); i++) {
                List<Message> msg = network.getMessgaes(userFriends.get(i).getId(), id);
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