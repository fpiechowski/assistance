package pl.mesayah.assistance.messaging;

import pl.mesayah.assistance.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A message that can be sent to a {@link Channel}.
 * <p>
 * Chat messages can be sent by {@link User}s to channels in order to communicate with other users subscribed to this
 * channels.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Message implements Serializable, pl.mesayah.assistance.Entity {

    private static final String ENTITY_NAME = "message";

    /**
     * An unique identifier of this chat message.
     */
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
    private LocalDateTime sendDateTime;

    /**
     * Content of this message.
     */
    private String textBody;

    /**
     * A channel this message was sent to.
     */
    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    /**
     * Constructs a chat message object with no attributes specified.
     */
    public Message() {

    }

    /**
     * @return an unique identifier of this chat message
     */
    @Override
    public Long getId() {

        return id;
    }

    @Override
    public String getEntityName() {

        return ENTITY_NAME;
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
    public LocalDateTime getSendDateTime() {

        return sendDateTime;
    }

    /**
     * @param sendDateTime a date this chat message is to be sent
     */
    public void setSendDateTime(LocalDateTime sendDateTime) {

        this.sendDateTime = sendDateTime;
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
    public int hashCode() {

        return Objects.hash(id, author, sendDateTime, textBody, channel);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message that = (Message) o;
        return id == that.id &&
                Objects.equals(author, that.author) &&
                Objects.equals(sendDateTime, that.sendDateTime) &&
                Objects.equals(textBody, that.textBody) &&
                Objects.equals(channel, that.channel);
    }
}
