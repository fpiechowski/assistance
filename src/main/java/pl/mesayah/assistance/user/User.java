package pl.mesayah.assistance.user;

import pl.mesayah.assistance.AbstractFilterableEntity;
import pl.mesayah.assistance.security.role.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A person who can use this application.
 * <p>
 * Users have a roles which defines their privileges to perform certain operations.
 */
@Entity
public class User extends AbstractFilterableEntity implements Serializable {


    /**
     * An unique identifier of this user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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
    private Set<Role> roles;
    /**
     * First name of this user.
     */
    private String firstName;
    /**
     * Last name of this user.
     */
    private String lastName;

    private String password;
    private boolean enabled;


    /**
     * Constructs a user object with no attributes specified.
     */
    public User() {

    }


    public User(String username, Set<Role> roles, String firstName, String lastName, String password, boolean enabled) {

        this.username = username;
        this.roles = roles;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.enabled = enabled;
    }


    @Override
    public int hashCode() {

        return Objects.hash(id, username, firstName, lastName, password, enabled);
    }


    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return enabled == user.enabled &&
                Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(password, user.password);
    }


    @Override
    public String toString() {

        return username;
    }


    /**
     * @return an unique identifier of this user
     */
    @Override
    public Long getId() {

        return id;
    }


    /**
     * @param id an unique identifier for this user
     */
    public void setId(Long id) {

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
    public Set<Role> getRoles() {

        return roles;
    }


    /**
     * @param roles a roles for this user
     */
    public void setRoles(Set<Role> roles) {

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


    public String getName() {

        return username;
    }


    @Override
    public String getTextRepresentation() {

        return firstName + " " + lastName + " " + username;
    }
}
