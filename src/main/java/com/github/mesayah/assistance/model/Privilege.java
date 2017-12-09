package com.github.mesayah.assistance.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A benefit of possibility to perform a specific operation.
 */
@Entity
public class Privilege implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "privilege_id")
    private long id;
    /**
     * Name of this privelege describing actions it permits.
     */
    private String name;

    public Privilege() {


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
