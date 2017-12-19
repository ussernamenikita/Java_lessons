package com.leti.social_net.commands.impl;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.NotAuthorized;
import com.leti.social_net.commands.Receiver;
import com.leti.social_net.services.NetworkService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Add user to friend
 * if user already friend do nothing
 */
@Service
public class AddToFriends implements Command {

    Logger logger = Logger.getLogger(AddToFriends.class);

    /**
     * Receiver instance
     */
    private final Receiver receiver ;

    /**
     * Token of logged user
     */
    private String token;





    @Autowired
    public AddToFriends(Receiver receiver) {
        this.receiver = receiver;
        this.token = receiver.getToken();
    }

    @Override
    public void execute() throws NotAuthorized {
        NetworkService networkService = receiver.getNetwork();
        String token = receiver.getToken();
        if(token == null)
        {
            throw new NotAuthorized("This operation requires authorization");
        }
        for (;;) {
            System.out.println("Enter the user id you want to add as a friend.");
            System.out.println("-1 for exit");
            int userId = receiver.getScanner().nextInt();
            if(userId == -1)
                break;
            networkService.addUserToFriend(token,userId);
            if(networkService.isFriends(token,userId))
            {
                logger.debug("Friend added successful");
                System.out.println("Successful");
            }else {
                logger.debug("Can't add to friend");
                System.out.println("Can't add to friend");
            }
        }
    }

    @Override
    public String getName() {
        return  "Add user to friends";
    }
}
