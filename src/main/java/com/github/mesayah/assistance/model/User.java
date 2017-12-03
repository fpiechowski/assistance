package com.github.mesayah.assistance.model;

import javax.persistence.*;

/**
 * Person who can use this application.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    /**
     * User name, login, credential used to sign in.
     */
    private String username;
    /**
     * Position of this user in an organization.
     */
    @ManyToOne
    @JoinColumn(name = "id")
    private Role role;
    /**
     * First name of this user.
     */
    private String firstName;
    /**
     * Last name of this user.
     */
    private String lastName;

    public User() {

    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public Role getRole() {

        return role;
    }

    public void setRole(Role role) {

        this.role = role;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }
}
