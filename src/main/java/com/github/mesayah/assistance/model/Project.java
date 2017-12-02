package com.github.mesayah.assistance.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private Date deadline;
    private Date starttime;
    private List<Task> tasks;
    @Enumerated(EnumType.ORDINAL)
    private Status status;
    private Set<User> members;
    private String description;
    private Milestone milestones;

    public Project(String name, Date deadline, Date starttime, List<Task> tasks, Status status, Set<User> members, String description, Milestone milestones) {
        this.name = name;
        this.deadline = deadline;
        this.starttime = starttime;
        this.tasks = tasks;
        this.status = status;
        this.members = members;
        this.description = description;
        this.milestones = milestones;
    }

    public Project() {


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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Milestone getMilestones() {
        return milestones;
    }

    public void setMilestones(Milestone milestones) {
        this.milestones = milestones;
    }
}

