package com.leti.social_net.commands.impl;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.Receiver;
import com.leti.social_net.dao.UserDao;
import com.leti.social_net.models.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Create new user command.
 * Send request to server save to database
 */
@Service
public class CreateUser implements Command{

    /**
     * Standard logger
     */
    private static final Logger logger = Logger.getLogger(CreateUser.class.getSimpleName());

    /**
     * Receiver instance
     */
    private final Receiver receiver ;

    @Autowired
    public CreateUser(Receiver receiver) {
        this.receiver = receiver;
    }


    @Override
    public void execute() {
        logger.info("Execute create user command");
        try {
            while (true) {
                User user = new User();
                System.out.println("Input username");
                String username = receiver.getScanner().next();
                if(receiver.getNetwork().userExist(username))
                {
                    System.out.println("User with username "+username+" already exists");
                    return;
                }
                user.setUsername(username);
                System.out.println("Input password");
                user.setPassword(receiver.getScanner().next());
                System.out.println("Input name");
                user.setName(receiver.getScanner().next());
                System.out.println("Input last name");
                user.setSurname(receiver.getScanner().next());
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(System.currentTimeMillis());
                user.setRegistered(dateFormat.format(date));
                int newId = Integer.MIN_VALUE;
                try {
                    newId = receiver.getNetwork().addUser(user);
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
                logger.info("user created with id " + newId);
                System.out.println("Successful create user");
                break;
            }
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
