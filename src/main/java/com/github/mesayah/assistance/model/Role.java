package com.github.mesayah.assistance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    /**
     * Name of this role.
     */
    private String name;
    /**
     * Privileges this role grants.
     */
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
}
