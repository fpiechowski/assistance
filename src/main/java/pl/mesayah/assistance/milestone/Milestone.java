package pl.mesayah.assistance.milestone;

import pl.mesayah.assistance.AbstractFilterableEntity;
import pl.mesayah.assistance.project.Project;
import pl.mesayah.assistance.task.Task;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

/**
 * A significant stage or an event in the development.
 * <p>
 * Milestones are part of projects. They are sets of tasks that need to be resolved to achieve a milestone.
 */
@Entity
public class Milestone extends AbstractFilterableEntity implements Serializable {

    private static final String ENTITY_NAME = "milestone";

    /**
     * An unique identifier of this milestone.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * The NAME of this milestone.
     */
    private String name;

    /**
     * A deadline for this milestone.
     */
    private Date deadline;

    /**
     * Tasks that have to be completed to achieve this milestone.
     */
    @ManyToMany
    @JoinTable(
            name = "milestone_task",
            joinColumns = @JoinColumn(name = "milestone_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private Set<Task> tasks;

    /**
     * A project this milestone indicates progress for.
     */
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


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
    @Override
    public Long getId() {

        return id;
    }


    @Override
    public String getEntityName() {

        return ENTITY_NAME;
    }


    /**
     * @param id an unique identifier for this milestone
     */
    public void setId(long id) {

        this.id = id;
    }


    /**
     * @return the NAME of this milestone
     */
    public String getName() {

        return name;
    }


    /**
     * @param name a NAME for this milestone
     */
    public void setName(String name) {

        this.name = name;
    }


    /**
     * @return a set of tasks this milestone requires to be resolved in order to be achieved
     */
    public Set<Task> getTasks() {

        return tasks;
    }


    /**
     * @param tasks a set of tasks to be resolved in order to achieve this milestone
     */
    public void setTasks(Set<Task> tasks) {

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
    public int hashCode() {

        return Objects.hash(id, name, tasks, project);
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
    public String getTextRepresentation() {

        return name;
    }
}
