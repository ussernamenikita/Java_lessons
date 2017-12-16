package com.leti.social_net.models;

import javax.persistence.*;

/**
 * Mwssage representation
 */
@Entity
public class Message {
    @Id
    @GeneratedValue
    private Integer id;


    @ManyToOne()
    private User userIdFrom;

    @ManyToOne()
    private User userIdTo;

    private String message;

    private Long sendTimestamp;

    private Long readTimestamp;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUserIdFrom() {
        return userIdFrom;
    }

    public void setUserIdFrom(User userIdFrom) {
        this.userIdFrom = userIdFrom;
    }

    public User getUserIdTo() {
        return userIdTo;
    }

    public void setUserIdTo(User userIdTo) {
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

    @Override
    public String toString() {
        return "Message{" +
                "userIdFrom=" + userIdFrom +
                ", userIdTo=" + userIdTo +
                ", message='" + message + '\'' +
                ", sendTimestamp=" + sendTimestamp +
                ", readTimestamp=" + readTimestamp +
                '}';
    }
}
