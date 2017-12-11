package pl.mesayah.assistance.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * A task that can be reported by a client.
 * <p>
 * An issue is just a subtype of task. It can be resolved the same way but are usually reported by clients.
 */
@Entity
public class Issue extends Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * A user who reported this issue.
     */
    @NotNull
    @ManyToOne
    @JoinColumn(name = "reporter_id")
    private User reportingUser;

    /**
     * Constructs a default issue object with no specified attributes.
     */
    public Issue() {

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Issue issue = (Issue) o;
        return id == issue.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public long getId() {


        return id;
    }

    @Override
    public void setId(long id) {

        this.id = id;
    }
}
