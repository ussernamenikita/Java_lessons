package com.leti.social_net;

/**
 * Current user config
 */
public class UserConfig {

    /**
     * Token received from server.
     * Use for identify user
     */
    private String token;

    private static UserConfig ourInstance = new UserConfig();

    public static UserConfig getInstance() {
        return ourInstance;
    }

    private UserConfig() {
    }
}
