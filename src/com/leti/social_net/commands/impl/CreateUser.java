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
        System.out.println("Input username");
        String username = receiver.getScanner().next();
        System.out.println("Input password");
        String password = receiver.getScanner().next();
        System.out.println("Input name");
        String name = receiver.getScanner().next();
        System.out.println("Input last name");
        String lastname = receiver.getScanner().next();
        User user = service.registerNewUser(username,password,name,lastname);
        if(user != null)
        {
            logger.info("User created with id "+user.getId());
            System.out.println("Successful create user");
        }else {
            logger.info("User not created");
            System.out.println("Error while creating user");
        }
        userDao.saveUsersInfo(user);
    }

    @Override
    public String getName() {
        return "Create new user";
    }
}
