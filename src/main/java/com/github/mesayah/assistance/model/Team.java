package com.github.mesayah.assistance.model;

import javax.persistence.*;
import java.util.Set;

/**
 * A group of users who works together.
 */
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    /**
     * Name of this team.
     */
    private String name;
    /**
     * Users who are members of this team.
     */
    @ManyToMany
    @JoinTable(name = "team_user", joinColumns = {@JoinColumn(name = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id")})
    private Set<User> members;


    public Team() {

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

    public Set<User> getMembers() {

        return members;
    }

    public void setMembers(Set<User> members) {

        this.members = members;
    }
}
