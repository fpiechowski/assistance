package pl.mesayah.assistance.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A group of {@link User}s who work together to develop a {@link Project}.
 */
@Entity
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Name of this team.
     */
    @NotNull
    private String name;

    /**
     * Users who are members of this team.
     */
    @ManyToMany
    @JoinTable(name = "team_user",
            joinColumns = {@JoinColumn(name = "team_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> members;


    public Team() {

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

    public Set<User> getMembers() {

        return members;
    }

    public void setMembers(Set<User> members) {

        this.members = members;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id &&
                Objects.equals(name, team.name) &&
                Objects.equals(members, team.members);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, members);
    }
}
