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
            logger.info("UserDaoImpl created with id "+user.getId());
            System.out.println("Successful create user");
        }else {
            logger.info("UserDaoImpl not created");
            System.out.println("Error while creating user");
        }
        userDao.insertOrUpdate(user);
    }

    @Override
    public String getName() {
        return "Create new user";
    }
}
