package com.github.mesayah.assistance.model;

import javax.persistence.Entity;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

/**
 * TaskType of chat message that is sent by application to notify user about an event.
 */
@Entity
public class Notification extends ChatMessage {

    /**
     * Title of this notification.
     */
    private String title;
    /**
     * Type of this notification.
     */
    private NotificationType type;
    /**
     * Date this notification was sent.
     */
    private Date sendDate;
    /**
     * Time this notification was sent.
     */
    private Time sendTime;

    /**
     * Constructs default notification entity with no specified parameters.
     */
    public Notification() {

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
