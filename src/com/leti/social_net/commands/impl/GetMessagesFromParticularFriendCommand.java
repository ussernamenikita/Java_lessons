package com.leti.social_net.commands.impl;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.Receiver;
import com.leti.social_net.models.Message;
import com.leti.social_net.models.Token;
import com.leti.social_net.models.services.NetworkService;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Nikita on 21.11.2017.
 */
public class GetMessagesFromParticularFriendCommand implements Command{
    private static final Logger logger = Logger.getLogger(GetMessagesFromParticularFriendCommand.class);

    private final Receiver receiver;
    private final String token;
    private List<Message> messages;
    private final Integer friendId;


    public GetMessagesFromParticularFriendCommand(Receiver receiver, String token, Integer friendId) {
        this.receiver = receiver;
        this.token = token;
        this.friendId = friendId;
    }

    @Override
    public void execute() {
        //TODO add logging
        Integer id = Token.getIdFromToken(token);
        if (id == null)
        {
            messages = null;
            return;
        }
        NetworkService network = receiver.getNetwork();
        if(!network.isFriends(id,friendId))
        {
            messages = null;
            return;
        }
        messages = network.getMessgaes(id,friendId);
    }
}
