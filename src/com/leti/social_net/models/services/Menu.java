package com.leti.social_net.models.services;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.Invoker;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Nikita on 22.11.2017.
 */
public class Menu {
    private final Invoker invoker;
    private final ArrayList<Command> commands;

    public Menu(Invoker invoker, ArrayList<Command> commands) {
        this.invoker = invoker;
        this.commands = commands;
    }

    public void showMenu() {
        try(Scanner scanner = new Scanner(System.in, "UTF-8")) {
            System.out.println("Enter the command number");
            int i = scanner.nextInt();
            invoker.setCommand(commands.get(i));
            invoker.run();
        }
    }
}
