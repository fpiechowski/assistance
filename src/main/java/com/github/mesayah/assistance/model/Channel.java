package com.github.mesayah.assistance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private List<User> participatingUsers;

    public Channel(String name, List<User> participatingUsers) {
        this.name = name;
        this.participatingUsers = participatingUsers;
    }

    public Channel(){


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

    public List<User> getParticipatingUsers() {
        return participatingUsers;
    }

    public void setParticipatingUsers(List<User> participatingUsers) {
        this.participatingUsers = participatingUsers;
    }
}
