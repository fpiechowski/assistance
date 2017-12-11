package pl.mesayah.assistance.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

/**
 * A significant stage or an event in the development.
 * <p>
 * Milestones are part of projects. They are sets of tasks that need to be resolved to achieve a milestone.
 */
@Entity
public class Milestone implements Serializable, Discussable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Name of this milestone describing a stage of progress or an event.
     */
    @NotNull
    private String name;

    /**
     * Deadline for this milestone.
     */
    @NotNull
    private Date deadline;

    /**
     * Tasks that have to be completed to achieve this milestone.
     */
    @NotNull
    @ManyToMany
    @JoinTable(
            name = "milestone_task",
            joinColumns = @JoinColumn(name = "milestone_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private List<Task> tasks;

    /**
     * A project this milestone describes progress for.
     */
    @NotNull
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    /**
     * A channel for discussing this milestone.
     */
    @NotNull
    @OneToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    public Milestone() {

    }

    public Date getDeadline() {

        return deadline;
    }

    public void setDeadline(Date deadline) {

        this.deadline = deadline;
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

    @Override
    public Channel getChannel() {

        return channel;
    }

    public void setChannel(Channel channel) {

        this.channel = channel;
    }
}
