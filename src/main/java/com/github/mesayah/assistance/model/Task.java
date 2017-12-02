package com.github.mesayah.assistance.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private User assigneeUser;
    @Enumerated(EnumType.ORDINAL)
    private Status status; //alt + enter
    private Priority priority;
    private Date deadline;
    private String description;
    private Task parentTask;
    @Enumerated(EnumType.ORDINAL)
    private Type type;


    public Task(String name, User assigneeUser, Status status, Priority priority, Date deadline, String description, Task parentTask, Type type) {
        this.name = name;
        this.assigneeUser = assigneeUser;
        this.status = status;
        this.priority = priority;
        this.deadline = deadline;
        this.description = description;
        this.parentTask = parentTask;
        this.type = type;
    }

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
}

// id, name, assignee user, status, priority, deadline, description, parent task, type (including issue type).