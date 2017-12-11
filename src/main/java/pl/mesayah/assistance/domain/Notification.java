package pl.mesayah.assistance.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

/**
 * A type of a chat message that is sent by the application to notify a user about an event.
 * <p>
 * Notifications are simply chat messages with an additional field for a title.
 */
@Entity
public class Notification extends ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Title of this notification.
     */
    @NotNull
    private String title;

    /**
     * Type of this notification.
     */
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private NotificationType type;

    /**
     * Date this notification was sent.
     */
    @NotNull
    private Date sendDate;

    /**
     * Time this notification was sent.
     */
    @NotNull
    private Time sendTime;

    /**
     * Constructs default notification entity with no specified parameters.
     */
    public Notification() {

    }

    @Override
    public long getId() {

        return id;
    }

    @Override
    public void setId(long id) {

        this.id = id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public NotificationType getType() {

        return type;
    }

    public void setType(NotificationType type) {

        this.type = type;
    }

    @Override
    public Date getSendDate() {

        return sendDate;
    }

    public void setSendDate(Date sendDate) {

        this.sendDate = sendDate;
    }

    @Override
    public Time getSendTime() {

        return sendTime;
    }

    @Override
    public void setSendTime(Time sendTime) {

        this.sendTime = sendTime;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Notification that = (Notification) o;
        return Objects.equals(title, that.title) &&
                type == that.type &&
                Objects.equals(sendDate, that.sendDate) &&
                Objects.equals(sendTime, that.sendTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), title, type, sendDate, sendTime);
    }
}
