package com.leti.social_net.commands.impl;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.Receiver;
import com.leti.social_net.models.services.DatabaseService;
import com.leti.social_net.models.services.NetworkService;
import javafx.util.Pair;
import org.apache.log4j.Logger;

/**
 * Login command, TODO handle result
 */
public class LoginCommand  implements Command{

    private static final Logger logger = Logger.getLogger(LoginCommand.class);

    private final Receiver receiver ;
    private final DatabaseService storage;
    private static Pair<String,String> LP;
    private String resultToken = null;

    public LoginCommand(Receiver receiver, DatabaseService storage,String login,String password) {
        this.receiver = receiver;
        this.storage = storage;
        if(login == null || password == null || login.isEmpty() || password.isEmpty())
        {
            logger.warn("Try login with null or empty login or password");
            throw new IllegalArgumentException("User and password can't be null or empty string!");
        }
        LP = new Pair<>(login,password);
    }


    @Override
    public void execute() {
        logger.info("Execute login command");
        NetworkService service = receiver.getNetwork();
        resultToken =  service.getToken(LP.getKey(),LP.getValue());
        logger.info("login command "+(resultToken!= null ? " success ": " fail "));
    }

    public String getResultToken() {
        return resultToken;
    }
}
