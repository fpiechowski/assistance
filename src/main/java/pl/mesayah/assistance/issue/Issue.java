package pl.mesayah.assistance.issue;

import pl.mesayah.assistance.task.Task;
import pl.mesayah.assistance.user.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * A task that can be reported by a client.
 * <p>
 * An issue is just a subtype of task. It can be resolved the same way but are usually reported by clients.
 */
@Entity
public class Issue extends Task {

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

    /**
     * @return a user who reported this issue
     */
    public User getReportingUser() {

        return reportingUser;
    }

    /**
     * @param reportingUser a user this issue is to be reported by
     */
    public void setReportingUser(User reportingUser) {

        this.reportingUser = reportingUser;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Issue issue = (Issue) o;
        return Objects.equals(reportingUser, issue.reportingUser);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), reportingUser);
    }
}
