package pl.mesayah.assistance.project;

import pl.mesayah.assistance.messaging.Channel;
import pl.mesayah.assistance.messaging.Discussable;
import pl.mesayah.assistance.milestone.Milestone;
import pl.mesayah.assistance.task.Task;
import pl.mesayah.assistance.team.Team;
import pl.mesayah.assistance.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/**
 * A planned piece of work that has a particular aim like achieving a goal or developing a product.
 * <p>
 * Projects are main entities in the application. {@link Team}s can work on a project by completing
 * {@link Task}s attached to it.
 */
@Entity
public class Project implements Serializable, Discussable {

    /**
     * An unique identifier of this project.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * A NAME of this project.
     */
    @NotNull
    private String name;

    /**
     * A description of this project.
     */
    private String description;

    /**
     * A date when this project ends.
     */
    private LocalDateTime deadline;

    /**
     * A date when this project starts.
     */
    @NotNull
    private LocalDateTime startTime;

    /**
     * A user who started a project.
     */
    @NotNull
    private User master;

    /**
     * Things to do to develop and realize this project.
     */
    @OneToMany(mappedBy = "project")
    private List<Task> tasks = new ArrayList<>();

    /**
     * A stage and progress indicator of this project.
     */
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Phase phase;

    /**
     * Groups of users that works on this project.
     */
    @ManyToMany
    @JoinTable(
            name = "project_team",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<Team> teams = new HashSet<>();

    /**
     * Significant events and achievements that indicates stages of progress of this project.
     */
    @OneToMany(mappedBy = "project")
    private List<Milestone> milestones = new ArrayList<>();

    /**
     * A place where this project is discussed by using a chat.
     */
    @NotNull
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "channel_id")
    private Channel channel;

    /**
     * Constructs a project object with no attributes specified.
     */
    public Project() {


    }

    /**
     * @return an unique identifier of this project
     */
    public long getId() {

        return id;
    }

    /**
     * @param id an unique identifier for this project
     */
    public void setId(long id) {

        this.id = id;
    }

    /**
     * @return the NAME of this project
     */
    public String getName() {

        return name;
    }

    /**
     * @param name a NAME for this project
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * @return a deadline date of this project
     */
    public LocalDateTime getDeadline() {

        return deadline;
    }

    /**
     * @param deadline a deadline date for this project
     */
    public void setDeadline(LocalDateTime deadline) {

        this.deadline = deadline;
    }

    /**
     * @return a date this project starts
     */
    public LocalDateTime getStartTime() {

        return startTime;
    }

    /**
     * @param startTime a date for this project to start
     */
    public void setStartTime(LocalDateTime startTime) {

        this.startTime = startTime;
    }

    /**
     * @return a list of tasks defined for this project
     */
    public List<Task> getTasks() {

        return tasks;
    }

    /**
     * @param tasks a list of task to be completed for this project
     */
    public void setTasks(List<Task> tasks) {

        this.tasks = tasks;
    }

    /**
     * @return a phase indicator of this project
     */
    public Phase getPhase() {

        return phase;
    }

    /**
     * @param phase a phase indicator for this project
     */
    public void setPhase(Phase phase) {

        this.phase = phase;
    }

    /**
     * @return a set of teams working on this project
     */
    public Set<Team> getTeams() {

        return teams;
    }

    /**
     * @param teams a set of teams to work on this project
     */
    public void setTeams(Set<Team> teams) {

        this.teams = teams;
    }

    /**
     * @return a description of this project
     */
    public String getDescription() {

        return description;
    }

    /**
     * @param description a description for this project
     */
    public void setDescription(String description) {

        this.description = description;
    }

    /**
     * @return a list of milestones defined for this project
     */
    public List<Milestone> getMilestones() {

        return milestones;
    }

    /**
     * @param milestones a list of milestones to be defined for this project
     */
    public void setMilestones(List<Milestone> milestones) {

        this.milestones = milestones;
    }

    @Override
    public Channel getChannel() {

        return channel;
    }

    /**
     * @param channel a channel where this project is discussed
     */
    public void setChannel(Channel channel) {

        this.channel = channel;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id &&
                Objects.equals(name, project.name) &&
                Objects.equals(description, project.description) &&
                Objects.equals(deadline, project.deadline) &&
                Objects.equals(startTime, project.startTime) &&
                Objects.equals(tasks, project.tasks) &&
                phase == project.phase &&
                Objects.equals(teams, project.teams) &&
                Objects.equals(milestones, project.milestones) &&
                Objects.equals(channel, project.channel);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, deadline, startTime, tasks, phase, teams, milestones, channel);
    }

    public enum Phase {

        PLANNING("Planning"),
        DESIGNING("Designing"),
        IMPLEMENTATION("Implementation"),
        TESTING("Testing"),
        INSTALLATION("Installation"),
        MAINTENANCE("Maintenance");

        private String name;

        Phase(String name) {

            this.name = name;
        }

        @Override
        public String toString() {

            return name;
        }

        public String getName() {

            return name;
        }
    }
}

