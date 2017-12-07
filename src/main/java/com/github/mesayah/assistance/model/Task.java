package com.github.mesayah.assistance.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Something to do.
 */
@Entity
public class Task implements Serializable, Discussable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id")
    private long id;
    /**
     * Name of this task.
     */
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
    @Enumerated(EnumType.ORDINAL)
    private Status status; //alt + enter
    /**
     * Importance of this task.
     */
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
     * Type of this task.
     */
    @Enumerated(EnumType.ORDINAL)
    private Type type;
    /**
     * Channel where this task is discussed.
     */
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "channel_id")
    private Channel channel;
    /**
     * A project this task involves.
     */
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    public Task() {


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

    public Type getType() {

        return type;
    }

    public void setType(Type type) {

        this.type = type;
    }

    @Override
    public Channel getChannel() {

        return channel;
    }

    public void setChannel(Channel channel) {

        this.channel = channel;
    }
}

// id, name, assignee user, status, priority, deadline, description, parent task, type (including issue type).