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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Name of this channel.
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

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Set<User> getSubscribedUsers() {

        return subscribedUsers;
    }

    public void setSubscribedUsers(Set<User> subscribedUsers) {

        this.subscribedUsers = subscribedUsers;
    }

    public List<ChatMessage> getMessages() {

        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {

        this.messages = messages;
    }
}
