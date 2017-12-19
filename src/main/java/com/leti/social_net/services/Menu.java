package com.leti.social_net.services;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.Invoker;
import com.leti.social_net.commands.NotAuthorized;
import com.leti.social_net.commands.Receiver;
import com.leti.social_net.commands.impl.*;
import com.leti.social_net.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Text menu in console
 */
@Service
public class Menu {
    private final Invoker invoker;
    private final List<Command> commands;

    @Autowired
    public Menu(Invoker invoker, List<Command> commands) {
        this.invoker = invoker;
        this.commands = commands;
    }

    public void showMenu() throws NotAuthorized {
            Scanner scanner = new Scanner(System.in, "UTF-8");
            int i = 1;
            while (i != -1) {
                for (int j = 0; j < commands.size(); j++) {
                    System.out.println(j + ":" + commands.get(j).getName());
                }
                System.out.println("-1: exit");
                try {
                    i = scanner.nextInt();
                }catch (NoSuchElementException e)
                {
                    scanner.next();
                    continue;
                }
                if (i >= 0 && i < commands.size()) {
                    invoker.setCommand(commands.get(i));
                    invoker.run();
                }
            }
    }




}
