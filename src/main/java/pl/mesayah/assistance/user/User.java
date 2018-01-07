package pl.mesayah.assistance.user;

import pl.mesayah.assistance.messaging.Channel;
import pl.mesayah.assistance.security.Role;
import pl.mesayah.assistance.todo.PersonalNote;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * A person who can use this application.
 * <p>
 * Users have a roles which defines their privileges to perform certain operations.
 */
@Entity
@Table
public class User implements Serializable {

    /**
     * An unique identifier of this user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * A credential this user uses to sign in.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * A roles of this user.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;


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
    private List<PersonalNote> personalNotes;

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

    private String password;

    private boolean enabled;

    /**
     * Constructs a user object with no attributes specified.
     */
    public User() {

    }

    public User(String username, Collection<Role> roles, String firstName, String lastName, String password, boolean enabled) {

        this.username = username;
        this.roles = roles;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.enabled = enabled;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, roles, firstName, lastName, personalNotes, subscribedChannels);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(username, user.username) &&
                Objects.equals(roles, user.roles) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(personalNotes, user.personalNotes) &&
                Objects.equals(subscribedChannels, user.subscribedChannels);
    }

    /**
     * @return a list of personal task this user is owner of
     */
    public List<PersonalNote> getPersonalNotes() {

        return personalNotes;
    }

    /**
     * @param personalNotes a list of personal task this user is to be an owner of
     */
    public void setPersonalNotes(List<PersonalNote> personalNotes) {

        this.personalNotes = personalNotes;
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
     * @return a roles of this user
     */
    public Collection<Role> getRoles() {

        return roles;
    }

    /**
     * @param roles a roles for this user
     */
    public void setRoles(Collection<Role> roles) {

        this.roles = roles;
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

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public boolean isEnabled() {

        return enabled;
    }

    public void setEnabled(boolean enabled) {

        this.enabled = enabled;
    }
}
