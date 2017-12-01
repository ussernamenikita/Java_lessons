package com.leti.social_net.commands;

import org.springframework.stereotype.Service;

/**
 * Command
 */
public interface Command {
    void execute();
    String getName();
}
