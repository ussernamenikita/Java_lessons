package com.leti.social_net.commands.impl;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.Receiver;
import com.leti.social_net.models.services.DatabaseService;

/**
 * Created by Nikita on 22.11.2017.
 */
public class AddToFriends implements Command {

    private final Receiver receiver ;
    private final DatabaseService databaseService;
    private String token;
    private int[] friends;


    public AddToFriends(Receiver receiver, DatabaseService databaseService, String token, int[] friends) {
        this.receiver = receiver;
        this.databaseService = databaseService;
        this.token = token;
        this.friends = friends;
    }

    @Override
    public void execute() {
        //TODO add log
    }
}
