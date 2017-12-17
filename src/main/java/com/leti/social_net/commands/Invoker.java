package com.leti.social_net.commands;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Command executor
 */
@Service
public class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void run() throws NotAuthorized {
        command.execute();
    }

}
