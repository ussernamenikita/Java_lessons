package com.leti.social_net.services;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.Invoker;
import com.leti.social_net.commands.Receiver;
import com.leti.social_net.commands.impl.*;
import com.leti.social_net.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Text menu in console
 */
@Service
public class Menu {
    private final Invoker invoker;
    private final ArrayList<Command> commands;

    @Autowired
    public Menu(Invoker invoker, ArrayList<Command> commands) {
        this.invoker = invoker;
        this.commands = commands;
    }

    public void showMenu() {
        try(Scanner scanner = new Scanner(System.in, "UTF-8")) {
            int i = 1;
            while (i != -1) {
                for (int j = 0; j < commands.size(); j++) {
                    System.out.println(j + ":" + commands.get(i).getName());
                }
                i = scanner.nextInt();
                if (i > 0 && i < commands.size()) {
                    invoker.setCommand(commands.get(i));
                    invoker.run();
                }
            }
        }
    }

    @Autowired
    @Bean
    public List<Command> getDafaulMenu(Receiver receiver, DatabaseService databaseService)
    {
        ArrayList<Command> cmds = new ArrayList<>(10);
        cmds.add(new LoginCommand(receiver,databaseService));
        cmds.add(new CreateUser(receiver,databaseService));
        cmds.add(new LoginCommand(receiver,databaseService));
        cmds.add(new AddToFriends(receiver,databaseService,"10"));
        cmds.add(new GetMyFriendsCommand(receiver));
        cmds.add(new GetAllMessageFromFriendsCommand(receiver));
        cmds.add(new GetMessagesFromParticularFriendCommand(receiver));
        cmds.add(new SendMessageToAllFriendsCommand(receiver,databaseService));
        cmds.add(new SendMessageToParticularUserCommand(receiver,databaseService));
        return cmds;
    }


}
