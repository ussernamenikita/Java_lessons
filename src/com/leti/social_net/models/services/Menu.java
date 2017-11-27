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
}
