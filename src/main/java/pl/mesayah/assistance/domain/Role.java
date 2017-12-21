package pl.mesayah.assistance.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A set of privileges to be able to perform some operations.
 * <p>
 * A role is granted to a {@link User} to indicate that he can perform specific operations.
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

    @Override
    public String toString() {

        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id &&
                Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}
