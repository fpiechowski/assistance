package com.github.mesayah.assistance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PersonalTask {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private PersonalTask parentPersonalTask;
    private String name;
    private boolean completed;

    public PersonalTask(PersonalTask parentPersonalTask, String name, boolean completed) {
        this.parentPersonalTask = parentPersonalTask;
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

    public PersonalTask getParentPersonalTask() {
        return parentPersonalTask;
    }

    public void setParentPersonalTask(PersonalTask parentPersonalTask) {
        this.parentPersonalTask = parentPersonalTask;
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
