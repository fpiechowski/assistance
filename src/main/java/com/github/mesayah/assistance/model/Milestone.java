package com.github.mesayah.assistance.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A significant stage or event in the development.
 */
@Entity
public class Milestone implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "milestone_id")
    private long id;
    /**
     * Name of this milestone describing a stage of progress or an event.
     */
    private String name;
    /**
     * Tasks that have to be completed to achieve this milestone.
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private List<Task> tasks;
    /**
     * A project this milestone describes progress for.
     */
    @ManyToOne
    @JoinColumn(name = "project_id")
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

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Milestone milestone = (Milestone) o;
        return id == milestone.id &&
                Objects.equals(name, milestone.name) &&
                Objects.equals(tasks, milestone.tasks) &&
                Objects.equals(project, milestone.project);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, tasks, project);
    }
}
