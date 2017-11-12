package com.leti.social_net.models;

/**
 * Mwssage representation
 */
public class Message {

    private Integer userIdFrom;
    private Integer userIdTo;
    private String message;
    private Integer sendTimestamp;
    private Integer readTimestamp;

    public Integer getUserIdFrom() {
        return userIdFrom;
    }

    public void setUserIdFrom(Integer userIdFrom) {
        this.userIdFrom = userIdFrom;
    }

    public Integer getUserIdTo() {
        return userIdTo;
    }

    public void setUserIdTo(Integer userIdTo) {
        this.userIdTo = userIdTo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getSendTimestamp() {
        return sendTimestamp;
    }

    public void setSendTimestamp(Integer sendTimestamp) {
        this.sendTimestamp = sendTimestamp;
    }

    public Integer getReadTimestamp() {
        return readTimestamp;
    }

    public void setReadTimestamp(Integer readTimestamp) {
        this.readTimestamp = readTimestamp;
    }

}
