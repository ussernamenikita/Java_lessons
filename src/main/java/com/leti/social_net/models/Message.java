package com.leti.social_net.models;

import javax.persistence.*;

/**
 * Message representation
 */
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "messages_id_gen")
    @SequenceGenerator(name = "messages_id_gen",sequenceName = "messages_id_seq")
    private Integer id;


    @ManyToOne()
    @JoinColumn(name = "useridfrom")
    private User useridfrom;

    @ManyToOne()
    @JoinColumn(name = "useridto")
    private User useridto;

    private String message;

    private Long sendTimestamp;

    private Long readTimestamp;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUseridfrom() {
        return useridfrom;
    }

    public void setUseridfrom(User useridfrom) {
        this.useridfrom = useridfrom;
    }

    public User getUseridto() {
        return useridto;
    }

    public void setUseridto(User useridto) {
        this.useridto = useridto;
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
                "useridfrom=" + useridfrom +
                ", useridto=" + useridto +
                ", message='" + message + '\'' +
                ", sendTimestamp=" + sendTimestamp +
                ", readTimestamp=" + readTimestamp +
                '}';
    }
}
