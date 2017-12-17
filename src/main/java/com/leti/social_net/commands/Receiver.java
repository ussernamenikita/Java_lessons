package com.leti.social_net.commands;

import com.leti.social_net.services.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Scanner;

/**
 * Receiver for command pattern
 */
@Service
public class Receiver {
    private final NetworkService network;

    public Scanner getScanner() {
        return scanner;
    }

    Scanner scanner = new Scanner(System.in, "UTF-8");

    String token = null;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    @Autowired
    public Receiver(NetworkService networkService) {
        this.network = networkService;
    }

    public NetworkService getNetwork() {
        return network;
    }
}
