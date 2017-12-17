package com.leti.social_net.commands.impl;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.Receiver;
import com.leti.social_net.services.NetworkService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Login command
 */
@Service
public class LoginCommand  implements Command{

    private static final Logger logger = Logger.getLogger(LoginCommand.class);

    private final Receiver receiver ;
    private String resultToken = null;

    @Autowired
    public LoginCommand(Receiver receiver) {
        this.receiver = receiver;

    }


    @Override
    public void execute() {
        logger.info("Execute login command");
        NetworkService service = receiver.getNetwork();
        System.out.println("Enter login");
        String login  = receiver.getScanner().next();
        System.out.println("Enter password");
        String password = receiver.getScanner().next();
        if(login == null || password == null || login.isEmpty() || password.isEmpty())
        {
            logger.warn("Try login with null or empty login or password");
            throw new IllegalArgumentException("Username and password can't be null or empty string!");
        }
        resultToken =  service.getToken(login,password);
        if(resultToken != null)
        {
            receiver.setToken(resultToken);
        }else
        {
            System.out.println("Invalid username or password");
        }
        logger.info("login command "+(resultToken!= null ? " success ": " fail "));
    }

    @Override
    public String getName() {
        return "Login";
    }

}
