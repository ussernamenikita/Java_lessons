package com.leti.social_net.commands.impl;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.Receiver;
import com.leti.social_net.models.services.DatabaseService;
import com.leti.social_net.models.services.NetworkService;
import javafx.util.Pair;

import java.util.logging.Logger;

public class LoginCommand  implements Command{

    private static final Logger logger = Logger.getLogger(LoginCommand.class.getSimpleName());

    private final Receiver receiver ;
    private final DatabaseService storage;
    private static Pair<String,String> LP;

    public LoginCommand(Receiver receiver, DatabaseService storage,String login,String password) {
        this.receiver = receiver;
        this.storage = storage;
        if(login == null || password == null || login.isEmpty() || password.isEmpty())
        {
            logger.warning("Try login with null or empty login or password");
            throw new IllegalArgumentException("User and password can't be null or empty string!");
        }
        LP = new Pair<>(login,password);
    }


    @Override
    public void execute() {
        logger.info("Execute login command");
        NetworkService service = receiver.getNetwork();
        service.login(LP.getKey(),LP.getValue());
    }
}
