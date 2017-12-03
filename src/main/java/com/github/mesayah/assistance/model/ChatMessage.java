package com.github.mesayah.assistance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;
import java.util.Date;

/**
 * A message that can be sent to a {@link Channel}.
 */
@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    /**
     * A user who wrote and sent this message.
     */
    private User author;
    /**
     * A day this message was sent.
     */
    private Date sendDate;
    /**
     * Time of day this message was sent.
     */
    private Time sendTime;
    /**
     * Content of this message.
     */
    private String textBody;
    /**
     * Channel this message was sent to.
     */
    private Channel channel;

    public ChatMessage(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Time getSendTime() {
        return sendTime;
    }

    public void setSendTime(Time sendTime) {
        this.sendTime = sendTime;
    }

    public String getTextBody() {
        return textBody;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
