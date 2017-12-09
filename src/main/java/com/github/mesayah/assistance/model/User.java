package com.github.mesayah.assistance.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Person who can use this application.
 */
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;
    /**
     * User name, login, credential used to sign in.
     */
    private String username;
    /**
     * Position of this user in an organization.
     */
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    /**
     * First name of this user.
     */
    private String firstName;
    /**
     * Last name of this user.
     */
    private String lastName;
    /**
     * List of this user's personal tasks.
     */
    @OneToMany(mappedBy = "owner")
    private List<PersonalTask> personalTasks;
    /**
     * Channels this user subscribes.
     */
    @ManyToMany
    @JoinTable(
            name = "channel_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "channel_id")
    )
    private Set<Channel> subscribedChannels;
    public User() {

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(username, user.username) &&
                Objects.equals(role, user.role) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(personalTasks, user.personalTasks) &&
                Objects.equals(subscribedChannels, user.subscribedChannels);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, role, firstName, lastName, personalTasks, subscribedChannels);
    }

    public List<PersonalTask> getPersonalTasks() {

        return personalTasks;
    }

    public void setPersonalTasks(List<PersonalTask> personalTasks) {

        this.personalTasks = personalTasks;
    }

    public Set<Channel> getSubscribedChannels() {

        return subscribedChannels;
    }

    public void setSubscribedChannels(Set<Channel> subscribedChannels) {

        this.subscribedChannels = subscribedChannels;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public Role getRole() {

        return role;
    }

    public void setRole(Role role) {

        this.role = role;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }
}
