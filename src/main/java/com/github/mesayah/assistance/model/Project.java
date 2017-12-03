package com.github.mesayah.assistance.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * A planned piece of work that has a particular aim like achieving a goal or developing a product.
 */
@Entity
public class Project implements Discussable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    /**
     * Name of this project.
     */
    private String name;
    /**
     * What this project is about.
     */
    private String description;
    /**
     * Date when this project ends.
     */
    private Date deadline;
    /**
     * Date when this project starts.
     */
    private Date startTime;
    /**
     * Things to do to develop and realize this project.
     */
    private List<Task> tasks;
    /**
     * Stage and progress indicator of this project.
     */
    @Enumerated(EnumType.ORDINAL)
    private Status status;
    /**
     * Groups of workers that works on this project.
     */
    private Set<Team> teams;
    /**
     * Significant events and achievements that indicates stages of progress of this project.
     */
    private Milestone milestones;
    /**
     * Place where this project is discussed using a chat.
     */
    private Channel channel;

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

    public Date getStartTime() {

        return startTime;
    }

    public void setStartTime(Date startTime) {

        this.startTime = startTime;
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

    public Set<Team> getTeams() {

        return teams;
    }

    public void setTeams(Set<Team> teams) {

        this.teams = teams;
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

    @Override
    public Channel getChannel() {

        return channel;
    }

    public void setChannel(Channel channel) {

        this.channel = channel;
    }
}

