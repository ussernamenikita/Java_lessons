package com.leti.social_net.commands;

/**
 * Created by Nikita on 12.11.2017.
 */
public class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void run()
    {
        command.execute();
    }


}
