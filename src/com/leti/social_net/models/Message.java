package com.leti.social_net.models;

/**
 * Mwssage representation
 */
public class Message {

    private Integer userIdFrom;
    private Integer userIdTo;
    private String message;
    private Long sendTimestamp;
    private Long readTimestamp;

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

    public Long getSendTimestamp() {
        return sendTimestamp;
    }

    public void setSendTimestamp(Long sendTimestamp) {
        this.sendTimestamp = sendTimestamp;
    }

    public Long getReadTimestamp() {
        return readTimestamp;
    }

    public void setReadTimestamp(Long readTimestamp) {
        this.readTimestamp = readTimestamp;
    }
}
