package com.leti.social_net.commands.impl;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.Receiver;
import com.leti.social_net.models.User;
import com.leti.social_net.models.services.DatabaseService;
import com.leti.social_net.models.services.NetworkService;

import java.util.logging.Logger;

/**
 * Create new user command.
 * Send request to server save to database
 */
public class CreateUser implements Command{
    private static final Logger logger = Logger.getLogger(CreateUser.class.getSimpleName());

    private final Receiver receiver ;
    private final DatabaseService userDao;

    public CreateUser(Receiver receiver, DatabaseService userDao) {
        this.receiver = receiver;
        this.userDao = userDao;
    }


    @Override
    public void execute() {
        logger.info("Execute create user command");
        NetworkService service = receiver.getNetwork();
        //TODO add data to user(name,lastname etc.)
        User user = service.registerNewUser("Username","password","Superman","Blablablaev");
        userDao.saveUsersInfo(user);
        logger.info("User created with id "+user.getId());
    }
}
