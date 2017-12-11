package pl.mesayah.assistance.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Something to do to develop a {@link Project}.
 * <p>
 * Task is a unit of work to be done in order to develop a project. They can be a part of milestones and can
 * have children tasks (subtasks) and one parent.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Task implements Serializable, Discussable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Name of this task.
     */
    @NotNull
    private String name;

    /**
     * User's responsible for completing this task.
     */
    @ManyToMany
    @JoinTable(
            name = "user_task",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> assigneeUsers;

    /**
     * Stage of progress this task is in.
     */
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    /**
     * Milestones this task is a part of.
     */
    @ManyToMany
    @JoinTable(
            name = "milestone_task",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "milestone_id")
    )
    private List<Milestone> milestones;

    /**
     * Importance of this task.
     */
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Priority priority;

    /**
     * Date when this task should be completed at the latest.
     */
    private Date deadline;

    /**
     * Description of this task, what it is about.
     */
    private String description;

    /**
     * The task this task is subtask of.
     */
    @ManyToOne
    @JoinColumn(name = "parent_task_id")
    private Task parentTask;

    /**
     * Subtasks of this task.
     */
    @OneToMany(mappedBy = "parentTask")
    private Set<Task> subtasks;

    /**
     * TaskType of this task.
     */
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private TaskType taskType;

    /**
     * Channel where this task is discussed.
     */
    @NotNull
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "channel_id")
    private Channel channel;

    /**
     * A project this task involves.
     */
    @NotNull
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Task() {


    }

    public List<Milestone> getMilestones() {

        return milestones;
    }

    public void setMilestones(List<Milestone> milestones) {

        this.milestones = milestones;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                Objects.equals(name, task.name) &&
                Objects.equals(assigneeUsers, task.assigneeUsers) &&
                status == task.status &&
                priority == task.priority &&
                Objects.equals(deadline, task.deadline) &&
                Objects.equals(description, task.description) &&
                Objects.equals(parentTask, task.parentTask) &&
                Objects.equals(subtasks, task.subtasks) &&
                taskType == task.taskType &&
                Objects.equals(channel, task.channel) &&
                Objects.equals(project, task.project);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, assigneeUsers, status, priority, deadline, description, parentTask, subtasks, taskType, channel, project);
    }

    public Set<Task> getSubtasks() {

        return subtasks;
    }

    public void setSubtasks(Set<Task> subtasks) {

        this.subtasks = subtasks;
    }

    public Project getProject() {

        return project;
    }

    public void setProject(Project project) {

        this.project = project;
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

    public Set<User> getAssigneeUsers() {

        return assigneeUsers;
    }

    public void setAssigneeUsers(Set<User> assigneeUsers) {

        this.assigneeUsers = assigneeUsers;
    }

    public Status getStatus() {

        return status;
    }

    public void setStatus(Status status) {

        this.status = status;
    }

    public Priority getPriority() {

        return priority;
    }

    public void setPriority(Priority priority) {

        this.priority = priority;
    }

    public Date getDeadline() {

        return deadline;
    }

    public void setDeadline(Date deadline) {

        this.deadline = deadline;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public Task getParentTask() {

        return parentTask;
    }

    public void setParentTask(Task parentTask) {

        this.parentTask = parentTask;
    }

    public TaskType getTaskType() {

        return taskType;
    }

    public void setTaskType(TaskType taskType) {

        this.taskType = taskType;
    }

    @Override
    public Channel getChannel() {

        return channel;
    }

    public void setChannel(Channel channel) {

        this.channel = channel;
    }
}

