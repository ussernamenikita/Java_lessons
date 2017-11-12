package com.leti.social_net.commands;

import com.leti.social_net.models.services.NetworkService;

/**
 * Created by Nikita on 12.11.2017.
 */
public class Receiver {
    private final NetworkService network;

    public Receiver(NetworkService networkService) {
        this.network = networkService;
    }

    public NetworkService getNetwork() {
        return network;
    }
}
