package com.leti.social_net;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.Invoker;
import com.leti.social_net.commands.Receiver;
import com.leti.social_net.commands.impl.*;
import com.leti.social_net.models.Message;
import com.leti.social_net.models.services.DatabaseService;
import com.leti.social_net.models.services.Menu;
import com.leti.social_net.models.services.Rating;
import com.leti.social_net.models.servicesImpl.DatabaseServicePlaceholder;
import com.leti.social_net.models.servicesImpl.NetworkPlaceholder;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Command> commands = new ArrayList<>(10);
        Receiver receiver = new Receiver(new NetworkPlaceholder());
        DatabaseService databaseService = new DatabaseServicePlaceholder();
        commands.add(new LoginCommand(receiver,databaseService));
        commands.add(new CreateUser(receiver,databaseService));
        commands.add(new LoginCommand(receiver,databaseService));
        commands.add(new AddToFriends(receiver,databaseService,"10"));
        commands.add(new GetMyFriendsCommand(receiver));
        commands.add(new GetAllMessageFromFriendsCommand(receiver));
        commands.add(new GetMessagesFromParticularFriendCommand(receiver));
        commands.add(new SendMessageToAllFriendsCommand(receiver,databaseService));
        commands.add(new SendMessageToParticularUserCommand(receiver,databaseService));
        new Menu(new Invoker(),commands);
    }
}
