package pl.mesayah.assistance.team;

import pl.mesayah.assistance.project.Project;
import pl.mesayah.assistance.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A group of {@link User}s who work together to develop a {@link Project}.
 */
@Entity
public class Team implements Serializable, pl.mesayah.assistance.Entity {

    /**
     * An unique identifier of this task.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * A NAME of this team.
     */
    private String name;

    private static final String ENTITY_NAME = "team";

    /**
     * Users who are members of this team.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "team_user",
            joinColumns = {@JoinColumn(name = "team_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> members;

    /**
     * Constructs a team object with no attributes specified.
     */
    public Team() {

    }

    /**
     * @return an unique identifier of this team
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
     * @param id an unique identifier for this team
     */


    public void setId(long id) {

        this.id = id;
    }

    /**
     * @return the NAME of this team
     */
    public String getName() {

        return name;
    }

    /**
     * @param name a NAME for this team
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * @return a collection of users being members of this team
     */
    public Set<User> getMembers() {

        return members;
    }

    /**
     * @param members a collection of users to be members of this team
     */
    public void setMembers(Set<User> members) {

        this.members = members;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, members);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id.equals(team.id) &&
                Objects.equals(name, team.name) &&
                Objects.equals(members, team.members);
    }
}
