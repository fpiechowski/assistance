package com.github.mesayah.assistance.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rolel_id")
    private long id;
    /**
     * Name of this role.
     */
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

    public Role() {

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

    public Set<Privilege> getPrivileges() {

        return privileges;
    }

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
