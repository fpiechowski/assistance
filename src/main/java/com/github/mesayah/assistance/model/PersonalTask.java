package com.github.mesayah.assistance.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A small, personal assignment that can be compared to traditional sticky note.
 */
@Entity
public class PersonalTask implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "personaltask_id")
    private long id;
    @OneToOne(cascade = CascadeType.REMOVE)
    /**
     * A personal task this personal task is a subtask of.
     */
    @ManyToOne
    @JoinColumn(name = "parent_personaltask_id")
    private PersonalTask parent;
    /**
     * Name of this personal task which describes its subject.
     */
    private String name;
    /**
     * A user who created this personal task.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;
    /**
     * Indicates whether this personal task is completed.
     */
    private boolean completed;

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

    public User getOwner() {

        return owner;
    }

    public void setOwner(User owner) {

        this.owner = owner;
    }
}
