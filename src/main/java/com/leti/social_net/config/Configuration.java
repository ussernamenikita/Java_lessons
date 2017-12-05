package com.leti.social_net.config;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.Receiver;
import com.leti.social_net.commands.impl.*;
import com.leti.social_net.dao.MessagesDao;
import com.leti.social_net.dao.UserDao;
import com.leti.social_net.services.DatabaseService;
import com.leti.social_net.services.NetworkService;
import com.leti.social_net.services.servicesImpl.NetworkPlaceholder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;

/**
 * Spring configuration file
 */
@org.springframework.context.annotation.Configuration
@ComponentScan("com.leti.social_net")
public class Configuration {

    @Bean
    NetworkService networdDao()
    {
        return new NetworkPlaceholder();
    }



    @Bean
    public ArrayList<Command> getDefaultMenu(Receiver receiver, DatabaseService databaseService, MessagesDao messagesDao, UserDao userDao)
    {
        ArrayList<Command> cmds = new ArrayList<>(10);
        cmds.add(new LoginCommand(receiver));
        cmds.add(new CreateUser(receiver,userDao));
        cmds.add(new AddToFriends(receiver,"10"));
        cmds.add(new GetMyFriendsCommand(receiver));
        cmds.add(new GetAllMessageFromFriendsCommand(receiver));
        cmds.add(new GetMessagesFromParticularFriendCommand(receiver));
        cmds.add(new SendMessageToAllFriendsCommand(receiver,messagesDao));
        cmds.add(new SendMessageToParticularUserCommand(receiver,messagesDao));
        return cmds;
    }
}
