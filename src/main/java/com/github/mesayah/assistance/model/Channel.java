package com.github.mesayah.assistance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.Set;

/**
 * A place {@link ChatMessage}s are sent to.
 */
@Entity
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private Set<User> subscribedUsers;
    /**
     * Messages sent to this channel.
     */
    private List<ChatMessage> messages;

    public Channel() {


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
