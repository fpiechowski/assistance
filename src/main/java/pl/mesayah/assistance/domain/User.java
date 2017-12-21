package pl.mesayah.assistance.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * A person who can use this application.
 * <p>
 * Users have a role which defines their privileges to perform certain operations.
 */
@Entity
public class User implements Serializable {

    /**
     * An unique identifier of this user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;

    /**
     * A credential this user uses to sign in.
     */
    @NotNull
    private String username;

    /**
     * A role of this user.
     */
    @NotNull
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
     * A list of this user's personal task.
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

    /**
     * Constructs a user object with no attributes specified.
     */
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

    /**
     * @return a list of personal task this user is owner of
     */
    public List<PersonalTask> getPersonalTasks() {

        return personalTasks;
    }

    /**
     * @param personalTasks a list of personal task this user is to be an owner of
     */
    public void setPersonalTasks(List<PersonalTask> personalTasks) {

        this.personalTasks = personalTasks;
    }

    /**
     * @return a set of channels this user is subscribed to
     */
    public Set<Channel> getSubscribedChannels() {

        return subscribedChannels;
    }

    /**
     * @param subscribedChannels a set of channels this user is to be subscribed to
     */
    public void setSubscribedChannels(Set<Channel> subscribedChannels) {

        this.subscribedChannels = subscribedChannels;
    }

    /**
     * @return an unique identifier of this user
     */
    public long getId() {

        return id;
    }

    /**
     * @param id an unique identifier for this user
     */
    public void setId(long id) {

        this.id = id;
    }

    /**
     * @return the username of this user
     */
    public String getUsername() {

        return username;
    }

    /**
     * @param username a username for this user
     */
    public void setUsername(String username) {

        this.username = username;
    }

    /**
     * @return a role of this user
     */
    public Role getRole() {

        return role;
    }

    /**
     * @param role a role for this user
     */
    public void setRole(Role role) {

        this.role = role;
    }

    /**
     * @return the first name of this user
     */
    public String getFirstName() {

        return firstName;
    }

    /**
     * @param firstName a first name for this user
     */
    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    /**
     * @return the last name of this user
     */
    public String getLastName() {

        return lastName;
    }

    /**
     * @param lastName a last name for this user
     */
    public void setLastName(String lastName) {

        this.lastName = lastName;
    }
}
