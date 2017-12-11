package pl.mesayah.assistance.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A benefit of possibility to perform a specific operation.
 * <p>
 * Privileges are connected with specific {@link Role}s to grant permissions to {@link User}s.
 */
@Entity
public class Privilege implements Serializable {

    /**
     * An unique identifier of this privilege.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Name of this privelege describing actions it permits.
     */
    @NotNull
    private String name;

    /**
     * Constructs a privilege object with no attributes specified.
     */
    public Privilege() {


    }

    /**
     * @return an unique identifier of this privilege
     */
    public long getId() {

        return id;
    }

    /**
     * @param id an unique identifier for this privilege
     */
    public void setId(long id) {

        this.id = id;
    }

    /**
     * @return the name of this privilege
     */
    public String getName() {

        return name;
    }

    /**
     * @param name a name for this privilege
     */
    public void setName(String name) {

        this.name = name;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Privilege privilege = (Privilege) o;
        return id == privilege.id &&
                Objects.equals(name, privilege.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}
