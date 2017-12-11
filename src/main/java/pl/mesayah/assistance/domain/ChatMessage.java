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
 * Chat messages can be sent by {@link User}s to channels in order to communicate with other users subscribed to this
 * channels.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ChatMessage implements Serializable {

    /**
     * An unique identifier of this chat message.
     */
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
     * A channel this message was sent to.
     */
    @NotNull
    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    /**
     * Constructs a chat message object with no attributes specified.
     */
    public ChatMessage() {

    }

    /**
     * @return an unique identifier of this chat message
     */
    public long getId() {

        return id;
    }

    /**
     * @param id an unique identifier for this chat message
     */
    public void setId(long id) {

        this.id = id;
    }

    /**
     * @return a user who wrote this chat message
     */
    public User getAuthor() {

        return author;
    }

    /**
     * @param author a user to be an author of this chat message
     */
    public void setAuthor(User author) {

        this.author = author;
    }

    /**
     * @return a date this chat message was sent
     */
    public Date getSendDate() {

        return sendDate;
    }

    /**
     * @param sendDate a date this chat message is to be sent
     */
    public void setSendDate(Date sendDate) {

        this.sendDate = sendDate;
    }

    /**
     * @return a time of day this message was sent
     */
    public Time getSendTime() {

        return sendTime;
    }

    /**
     * @param sendTime a time of day this this message is to be sent
     */
    public void setSendTime(Time sendTime) {

        this.sendTime = sendTime;
    }

    /**
     * @return content of this chat message
     */
    public String getTextBody() {

        return textBody;
    }

    /**
     * @param textBody content for this chat message
     */
    public void setTextBody(String textBody) {

        this.textBody = textBody;
    }

    /**
     * @return a channel this chat message was sent to
     */
    public Channel getChannel() {

        return channel;
    }

    /**
     * @param channel a channel this chat message is to be sent to
     */
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
