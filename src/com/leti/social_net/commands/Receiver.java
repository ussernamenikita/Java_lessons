package com.leti.social_net.commands;

import com.leti.social_net.models.services.NetworkService;

import java.util.Scanner;

/**
 * Created by Nikita on 12.11.2017.
 */
public class Receiver {
    private final NetworkService network;

    public Scanner getScanner() {
        return scanner;
    }

    Scanner scanner = new Scanner(System.in, "UTF-8");


    public Receiver(NetworkService networkService) {
        this.network = networkService;
    }

    public NetworkService getNetwork() {
        return network;
    }
}
