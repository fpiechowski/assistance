package com.github.mesayah.assistance.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PersonalTask implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @OneToOne(cascade = CascadeType.REMOVE)
    private PersonalTask parent;
    private String name;
    private boolean completed;

    public PersonalTask(PersonalTask parent, String name, boolean completed) {

        this.parent = parent;
        this.name = name;
        this.completed = completed;
    }

    public PersonalTask() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PersonalTask getParent() {

        return parent;
    }

    public void setParent(PersonalTask parent) {

        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {

        return completed;
    }

    public void setCompleted(boolean completed) {

        this.completed = completed;
    }
}
