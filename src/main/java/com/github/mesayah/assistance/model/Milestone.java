package com.github.mesayah.assistance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

/**
 * A significant stage or event in the development.
 */
@Entity
public class Milestone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    /**
     * Name of this milestone describing a stage of progress or an event.
     */
    private String name;
    /**
     * Tasks that have to be completed to achieve this milestone.
     */
    private List<Task> tasks;
    /**
     * A project this milestone describes progress for.
     */
    private Project project;

    public Milestone() {

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

    public List<Task> getTasks() {

        return tasks;
    }

    public void setTasks(List<Task> tasks) {

        this.tasks = tasks;
    }

    public Project getProject() {

        return project;
    }

    public void setProject(Project project) {

        this.project = project;
    }
}
