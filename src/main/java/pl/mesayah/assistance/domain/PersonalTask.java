package pl.mesayah.assistance.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A small, personal assignment that can be compared to traditional sticky note.
 * <p>
 * Personal tasks are managed only by the user who created them.
 */
@Entity
public class PersonalTask implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * A personal task this personal task is a subtask of.
     */
    @ManyToOne
    @JoinColumn(name = "parent_personaltask_id")
    private PersonalTask parent;

    /**
     * Name of this personal task which describes its subject.
     */
    @NotNull
    private String name;

    /**
     * A user who created this personal task.
     */
    @NotNull
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    /**
     * Indicates whether this personal task is completed.
     */
    @NotNull
    private boolean completed;

    public PersonalTask() {

    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public PersonalTask getParent() {

        return parent;
    }

    public void setParent(PersonalTask parent) {

        this.parent = parent;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public boolean isCompleted() {

        return completed;
    }

    public void setCompleted(boolean completed) {

        this.completed = completed;
    }

    public User getOwner() {

        return owner;
    }

    public void setOwner(User owner) {

        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalTask that = (PersonalTask) o;
        return id == that.id &&
                completed == that.completed &&
                Objects.equals(parent, that.parent) &&
                Objects.equals(name, that.name) &&
                Objects.equals(owner, that.owner);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, parent, name, owner, completed);
    }
}
