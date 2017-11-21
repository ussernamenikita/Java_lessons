package com.leti.social_net.commands.impl;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.Receiver;
import com.leti.social_net.models.User;
import com.leti.social_net.models.services.DatabaseService;
import com.leti.social_net.models.services.NetworkService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikita on 21.11.2017.
 */
public class GetMyFriendsCommand implements Command {

    private static final Logger logger = Logger.getLogger(GetMyFriendsCommand.class);

    private final Receiver receiver ;
    private final String token;
    private List<User> userFriends;

    public GetMyFriendsCommand(Receiver receiver, String token) {
        this.receiver = receiver;
        this.token = token;
    }

    @Override
    public void execute() {
        logger.info("Execute command get my user");
        NetworkService network = receiver.getNetwork();
        userFriends = network.getUserFriends(token,network.getFriendsCount(token),0);
        logger.info("Receive "+(userFriends == null ? 0 : userFriends.size())+" freinds");
    }
}
