package pl.mesayah.assistance.todo;

import pl.mesayah.assistance.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A small, personal assignment that can be compared to traditional sticky note.
 * <p>
 * Personal tasks are managed only by the user who created them.
 */
@Entity
public class PersonalNote implements Serializable {

    /**
     * An unique identifier of this personal task.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * A personal task this personal task is a subtask of.
     */
    @ManyToOne
    @JoinColumn(name = "parent_personaltask_id")
    private PersonalNote parent;

    /**
     * The NAME of this personal task which describes its subject.
     */
    private String name;

    /**
     * A user who created this personal task.
     */
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    /**
     * Indicates whether this personal task is completed.
     */
    private boolean completed;

    /**
     * Constructs a personal task object with no attributes specified.
     */
    public PersonalNote() {

    }

    /**
     * @return an unique identifier of this personal task
     */
    public long getId() {

        return id;
    }

    /**
     * @param id an unique identifier for this personal task
     */
    public void setId(long id) {

        this.id = id;
    }

    /**
     * @return a parent task of this personal task
     */
    public PersonalNote getParent() {

        return parent;
    }

    /**
     * @param parent a parent task for this personal task
     */
    public void setParent(PersonalNote parent) {

        this.parent = parent;
    }

    /**
     * @return the NAME of this personal task
     */
    public String getName() {

        return name;
    }

    /**
     * @param name a NAME for this personal task
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * @return true if this personal task is completed
     */
    public boolean isCompleted() {

        return completed;
    }

    /**
     * @param completed a logic value whether this task is to be completed
     */
    public void setCompleted(boolean completed) {

        this.completed = completed;
    }

    /**
     * @return a user who created and owns this personal task
     */
    public User getOwner() {

        return owner;
    }

    /**
     * @param owner a user to be the owner of this personal task
     */
    public void setOwner(User owner) {

        this.owner = owner;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, parent, name, owner, completed);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalNote that = (PersonalNote) o;
        return id == that.id &&
                completed == that.completed &&
                Objects.equals(parent, that.parent) &&
                Objects.equals(name, that.name) &&
                Objects.equals(owner, that.owner);
    }
}
