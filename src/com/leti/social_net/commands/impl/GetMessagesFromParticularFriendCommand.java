package com.leti.social_net.commands.impl;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.Receiver;
import com.leti.social_net.models.Message;
import com.leti.social_net.models.Token;
import com.leti.social_net.models.services.NetworkService;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Scanner;

/**
 * Get messages from particular friend
 */
public class GetMessagesFromParticularFriendCommand implements Command{
    private static final Logger logger = Logger.getLogger(GetMessagesFromParticularFriendCommand.class);

    private final Receiver receiver;
    private List<Message> messages;





    public GetMessagesFromParticularFriendCommand(Receiver receiver) {
        this.receiver = receiver;

    }

    @Override
    public void execute() {
        System.out.println("Enter your token");
        String token = receiver.getScanner().next();
        System.out.printf("Enter friend id");
        Integer friendId = receiver.getScanner().nextInt();
        logger.info("Try to get message from user with token "+token+" and user with id"+friendId);
        Integer id = Token.getIdFromToken(token);
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
        messages = network.getMessgaes(id,friendId);
        logger.info("Received "+messages.size()+" messages");
        messages.forEach(System.out::println);

    }

    @Override
    public String getName() {
        return "Get all messages from particular friend";
    }
}
