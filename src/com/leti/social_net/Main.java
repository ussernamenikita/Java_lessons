package com.leti.social_net;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.Receiver;
import com.leti.social_net.commands.impl.*;
import com.leti.social_net.models.services.DatabaseService;
import com.leti.social_net.models.servicesImpl.DatabaseServicePlaceholder;
import com.leti.social_net.models.servicesImpl.NetworkPlaceholder;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ArrayList<Command> commands = new ArrayList<>(10);
        Receiver receiver = new Receiver(new NetworkPlaceholder());
        DatabaseService databaseService = new DatabaseServicePlaceholder();
        commands.add(new CreateUser(receiver,databaseService));
        commands.add(new LoginCommand(receiver,databaseService,"username","password"));
        commands.add(new AddToFriends(receiver,databaseService,"10",{1,3,4,7}));
        commands.add(new GetMyFriendsCommand(receiver,"10"));
        commands.add(new GetAllMessageFromFriendsCommad(receiver,"10"));
        commands.add(new GetMessagesFromParticularFriendCommand(receiver,"10",Integer.MAX_VALUE));
        commands.add(new SendMessageToAllFriendsCommand(receiver,databaseService,"Hello !","10"));
        commands.add(new SendMessageToParticularUserCommand(receiver,databaseService,"Hi","10",Integer.MAX_VALUE));
    }
}
