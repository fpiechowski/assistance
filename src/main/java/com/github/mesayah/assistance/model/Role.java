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
    private String name;
    private Set<User> users;
    private Set<Privilege> privileges;

    public Role(String name, Set<User> users, Set<Privilege> privileges) {

        this.name = name;
        this.users = users;
        this.privileges = privileges;
    }

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

    public Set<User> getUsers() {

        return users;
    }

    public void setUsers(Set<User> users) {

        this.users = users;
    }

    public Set<Privilege> getPrivileges() {

        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {

        this.privileges = privileges;
    }
}
