package com.github.mesayah.assistance.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

/**
 * A message that can be sent to a {@link Channel}.
 */
@Entity
public class ChatMessage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "chatmessage_id")
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
    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    public ChatMessage() {

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

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessage that = (ChatMessage) o;
        return id == that.id &&
                Objects.equals(author, that.author) &&
                Objects.equals(sendDate, that.sendDate) &&
                Objects.equals(sendTime, that.sendTime) &&
                Objects.equals(textBody, that.textBody) &&
                Objects.equals(channel, that.channel);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, author, sendDate, sendTime, textBody, channel);
    }
}
