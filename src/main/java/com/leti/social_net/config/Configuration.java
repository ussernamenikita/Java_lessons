package com.leti.social_net.config;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.Invoker;
import com.leti.social_net.commands.Receiver;
import com.leti.social_net.services.DatabaseService;
import com.leti.social_net.services.Menu;
import com.leti.social_net.services.servicesImpl.DatabaseServicePlaceholder;
import com.leti.social_net.services.servicesImpl.NetworkPlaceholder;
import com.leti.social_net.services.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;

/**
 * Spring configuration file
 */
@org.springframework.context.annotation.Configuration
@ComponentScan("social_net,models,services,dao,services")
public class Configuration {

    @Bean
    NetworkService networdDao()
    {
        return new NetworkPlaceholder();
    }

    @Bean
    @Autowired
    Receiver receiver(NetworkService networkService)
    {
        return new Receiver(networkService);
    }

    @Bean
    DatabaseService databaseService()
    {
        return  new DatabaseServicePlaceholder();
    }

    @Bean
    @Autowired
    Menu getMenu(Invoker invoker, ArrayList<Command> commands)
    {
        return new Menu(invoker,commands);
    }
}
