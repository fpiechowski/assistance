package com.github.mesayah.assistance.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * A place {@link ChatMessage}s are sent to.
 */
@Entity
public class Channel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "channel_id")
    private long id;
    /**
     * Name of this channel.
     * If channel is made for a task or a whole project the name for it is generated basing on project's or task's
     * name.
     */
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
