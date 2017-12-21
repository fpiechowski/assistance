package pl.mesayah.assistance.milestone;

import pl.mesayah.assistance.messaging.Channel;
import pl.mesayah.assistance.messaging.Discussable;
import pl.mesayah.assistance.project.Project;
import pl.mesayah.assistance.task.Task;

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

    /**
     * An unique identifier of this milestone.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * The name of this milestone.
     */
    @NotNull
    private String name;

    /**
     * A deadline for this milestone.
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
     * A project this milestone indicates progress for.
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

    /**
     * Constructs a milestone object with no attributes specified.
     */
    public Milestone() {

    }

    /**
     * @return a deadline date of this milestone
     */
    public Date getDeadline() {

        return deadline;
    }

    /**
     * @param deadline a deadline date for this milestone
     */
    public void setDeadline(Date deadline) {

        this.deadline = deadline;
    }

    /**
     * @return an unique identifier of this milestone
     */
    public long getId() {

        return id;
    }

    /**
     * @param id an unique identifier for this milestone
     */
    public void setId(long id) {

        this.id = id;
    }

    /**
     * @return the name of this milestone
     */
    public String getName() {

        return name;
    }

    /**
     * @param name a name for this milestone
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * @return a list of tasks this milestone requires to be resolved in order to be achieved
     */
    public List<Task> getTasks() {

        return tasks;
    }

    /**
     * @param tasks a list of tasks to be resolved in order to achieve this milestone
     */
    public void setTasks(List<Task> tasks) {

        this.tasks = tasks;
    }

    /**
     * @return a project this milestone indicates progress for
     */
    public Project getProject() {

        return project;
    }

    /**
     * @param project a project this milestone is to indicate progress for
     */
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

    /**
     * @param channel a channel where this milestone is discussed
     */
    public void setChannel(Channel channel) {

        this.channel = channel;
    }
}
