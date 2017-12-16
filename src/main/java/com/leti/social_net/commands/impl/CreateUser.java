package com.leti.social_net.commands.impl;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.Receiver;
import com.leti.social_net.dao.UserDao;
import com.leti.social_net.models.User;
import com.leti.social_net.services.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * Create new user command.
 * Send request to server save to database
 */
@Service
public class CreateUser implements Command{
    private static final Logger logger = Logger.getLogger(CreateUser.class.getSimpleName());

    private final Receiver receiver ;
    private final UserDao userDao;

    @Autowired
    public CreateUser(Receiver receiver, UserDao userDao) {
        this.receiver = receiver;
        this.userDao = userDao;
    }


    @Override
    public void execute() {
        logger.info("Execute create user command");
        try {
            User user = new User();
            System.out.println("Input username");
            user.setUsername(receiver.getScanner().next());
            System.out.println("Input password");
            user.setPassword(receiver.getScanner().next());
            System.out.println("Input name");
            user.setName(receiver.getScanner().next());
            System.out.println("Input last name");
            user.setSurname(receiver.getScanner().next());
            int newId = userDao.insertOrUpdate(user);
            logger.info("user created with id " + newId);
            System.out.println("Successful create user");
        }catch (Exception e)
        {
            logger.info("user not created");
            System.out.println("Error while creating user");
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "Create new user";
    }
}
