package com.leti.social_net.commands;
/**
 * Command
 * interface
 */
public interface Command {





    /**
     * Execute the commande
     * @throws NotAuthorized if need authorization but user not logged yet
     */
    void execute() throws NotAuthorized;

    /**
     * Get text name of command
     * @return text name of the command
     */
    String getName();
}
