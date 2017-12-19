package com.leti.social_net.commands;

/**
 * Exception if user
 * needs to login
 */
public class NotAuthorized extends Exception {


    /**
     * Default constructors
     * @param message error message
     */
    public NotAuthorized(String message) {
        super(message);
    }

    /**
     * Default constructors
     * @param message error message
     * @param cause cause
     */
    public NotAuthorized(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Default constructors
     * @param cause cause
     */
    public NotAuthorized(Throwable cause) {
        super(cause);
    }

    /**
     * Default constructors
     * @param message error message
     * @param cause error cause
     * @param enableSuppression i don't know what is it
     * @param writableStackTrace don't know what is it
     */
    public NotAuthorized(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
