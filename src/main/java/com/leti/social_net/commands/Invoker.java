package com.leti.social_net.commands;
import org.springframework.stereotype.Service;

/**
 * Command
 * executor
 */
@Service
public class Invoker {

    /**
     *  Command for execution
     */
    private Command command;

    /**
     * Set new command
     * @param command new command
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * Execute command
     * @throws NotAuthorized if need authorixation but user not logged yet
     */
    public void run() throws NotAuthorized {
        command.execute();
    }

}
