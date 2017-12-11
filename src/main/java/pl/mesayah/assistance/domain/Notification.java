package pl.mesayah.assistance.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * A type of a chat message that is sent by the application to notify a user about an event.
 * <p>
 * Notifications are simply chat messages with an additional field for a title.
 */
@Entity
public class Notification extends ChatMessage {

    /**
     * A title of this notification.
     */
    @NotNull
    private String title;

    /**
     * A type of this notification.
     */
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private NotificationType type;

    /**
     * Constructs notification object with no specified attributes.
     */
    public Notification() {

    }

    /**
     * @return a title of this notification
     */
    public String getTitle() {

        return title;
    }

    /**
     * @param title a title for this notification
     */
    public void setTitle(String title) {

        this.title = title;
    }

    /**
     * @return a type of this notification
     */
    public NotificationType getType() {

        return type;
    }

    /**
     * @param type a type for this notification
     */
    public void setType(NotificationType type) {

        this.type = type;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Notification that = (Notification) o;
        return Objects.equals(title, that.title) &&
                type == that.type;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), title, type);
    }
}
