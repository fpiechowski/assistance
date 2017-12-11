package pl.mesayah.assistance.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A set of privileges to be able to perform some operations.
 * <p>
 * A role is granted to a {@link User} to indicate that he can perform operations related to role's {@link Privilege}s.
 */
@Entity
public class Role implements Serializable {

    /**
     * An unique identifier of this project.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * A name of this role.
     */
    @NotNull
    private String name;

    /**
     * Privileges this role grants.
     */
    @ManyToMany
    @JoinTable(
            name = "role_privilege",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id")
    )
    private Set<Privilege> privileges;

    /**
     * Constructs a role object with no attributes specified.
     */
    public Role() {

    }

    /**
     * @return an unique identifier of this role
     */
    public long getId() {

        return id;
    }

    /**
     * @param id an unique identifier for this role
     */
    public void setId(long id) {

        this.id = id;
    }

    /**
     * @return the name of this project
     */
    public String getName() {

        return name;
    }

    /**
     * @param name a name for this project
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * @return a set of privileges this role grants
     */
    public Set<Privilege> getPrivileges() {

        return privileges;
    }

    /**
     * @param privileges a set of privileges to be granted by this role
     */
    public void setPrivileges(Set<Privilege> privileges) {

        this.privileges = privileges;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id &&
                Objects.equals(name, role.name) &&
                Objects.equals(privileges, role.privileges);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, privileges);
    }
}
