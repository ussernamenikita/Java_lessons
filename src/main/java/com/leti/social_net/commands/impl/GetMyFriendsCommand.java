package com.leti.social_net.commands.impl;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.NotAuthorized;
import com.leti.social_net.commands.Receiver;
import com.leti.social_net.models.User;
import com.leti.social_net.services.NetworkService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Get all friend for logged user
 * and output it to console
 */
@Service
public class GetMyFriendsCommand implements Command {

    /**
     * Standrd logger
     */
    private static final Logger logger = Logger.getLogger(GetMyFriendsCommand.class);

    /**
     * Receiver reference
     */
    private final Receiver receiver ;

    /**
     * Receiver friends
     */
    private List<User> userFriends;

    @Autowired
    public GetMyFriendsCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() throws NotAuthorized {
        logger.info("Execute command get my user");
        String token = receiver.getToken();
        if(token == null)
        {
            throw new NotAuthorized("This operation requires authorization");
        }
        NetworkService network = receiver.getNetwork();
        userFriends = network.getUserFriends(token,network.getFriendsCount(token),0);
        if(userFriends != null) {
            userFriends.forEach(System.out::println);
        }
        logger.info("Receive "+(userFriends == null ? 0 : userFriends.size())+" freinds");
    }

    @Override
    public String getName() {
        return "Get my friends";
    }
}
