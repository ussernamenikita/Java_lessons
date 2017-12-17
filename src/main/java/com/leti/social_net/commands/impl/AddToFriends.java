package com.leti.social_net.commands.impl;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.NotAuthorized;
import com.leti.social_net.commands.Receiver;
import com.leti.social_net.services.DatabaseService;
import com.leti.social_net.services.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Add user to friend
 */
@Service
public class AddToFriends implements Command {

    private final Receiver receiver ;
    private String token;





    @Autowired
    public AddToFriends(Receiver receiver) {
        this.receiver = receiver;
        this.token = receiver.getToken();
    }

    @Override
    public void execute() throws NotAuthorized {
        //TODO add log
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
                System.out.println("Successful");
            }else {
                System.out.println("Can't add to friend");
            }
        }
    }

    @Override
    public String getName() {
        return  "Add user to friends";
    }
}
