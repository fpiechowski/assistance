package pl.mesayah.assistance.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

/**
 * A message that can be sent to a {@link Channel}.
 * <p>
 * Chat messages can be sent by {@link User}s to channels to communicate with other users subscribed to the
 * channel.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ChatMessage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * A user who wrote and sent this message.
     */
    @NotNull
    private User author;

    /**
     * A day this message was sent.
     */
    @NotNull
    private Date sendDate;

    /**
     * Time of day this message was sent.
     */
    @NotNull
    private Time sendTime;

    /**
     * Content of this message.
     */
    private String textBody;

    /**
     * Channel this message was sent to.
     */
    @NotNull
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
