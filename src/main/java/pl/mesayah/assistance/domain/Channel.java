package pl.mesayah.assistance.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * A place where {@link ChatMessage}s are sent to.
 * <p>
 * Channels can be created as custom channels with a custom name or be automatically created based on a
 * {@link Discussable} object. When a discussable object is created there is also a channel created for that object.
 * {@link User}s can subscribe to channels to be notified about new messages there.
 */
@Entity
public class Channel implements Serializable {

    /**
     * An unique identifier of this channel.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * A name of this channel.
     * <p>
     * If a channel is made for a discussable object the name is generated based on the discussable object's name.
     */
    @NotNull
    private String name;

    /**
     * Users to be notified about new messages on this channel.
     */
    @ManyToMany
    @JoinTable(
            name = "channel_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "channel_id")
    )
    private Set<User> subscribedUsers;

    /**
     * Messages sent to this channel.
     */
    @OneToMany(mappedBy = "channel")
    private List<ChatMessage> messages;

    /**
     * Constructs a channel object with no attributes specified.
     */
    public Channel() {


    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Channel channel = (Channel) o;
        return id == channel.id &&
                Objects.equals(name, channel.name) &&
                Objects.equals(subscribedUsers, channel.subscribedUsers) &&
                Objects.equals(messages, channel.messages);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, subscribedUsers, messages);
    }

    /**
     * @return an unique identifier of this channel
     */
    public long getId() {

        return id;
    }

    /**
     * @param id an unique identifier for this channel
     */
    public void setId(long id) {

        this.id = id;
    }

    /**
     * @return a name of this channel
     */
    public String getName() {

        return name;
    }

    /**
     * @param name a name for this channel
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * @return a set of users subscribed to this channel
     */
    public Set<User> getSubscribedUsers() {

        return subscribedUsers;
    }

    /**
     * @param subscribedUsers a set of users to be subscribed to this channel
     */
    public void setSubscribedUsers(Set<User> subscribedUsers) {

        this.subscribedUsers = subscribedUsers;
    }

    /**
     * @return a list of messages sent to this channel
     */
    public List<ChatMessage> getMessages() {

        return messages;
    }

    /**
     * @param messages a list of messages to be sent to this channel
     */
    public void setMessages(List<ChatMessage> messages) {

        this.messages = messages;
    }
}
