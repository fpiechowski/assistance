package com.github.mesayah.assistance.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Something to do.
 */
@Entity
public class Task implements Discussable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    /**
     * Name of this task.
     */
    private String name;
    /**
     * User's responsible for completing this task.
     */
    private User assigneeUser;
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
    private Task parentTask;
    /**
     * Type of this task.
     */
    @Enumerated(EnumType.ORDINAL)
    private Type type;
    /**
     * Channel where this task is discussed.
     */
    private Channel channel;


    public Task() {


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

    public User getAssigneeUser() {

        return assigneeUser;
    }

    public void setAssigneeUser(User assigneeUser) {

        this.assigneeUser = assigneeUser;
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