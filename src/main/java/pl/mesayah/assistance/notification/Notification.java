package pl.mesayah.assistance.notification;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A type of a chat message that is sent by the application to notify a user about an event.
 * <p>
 * Notifications are simply chat messages with an additional field for a title.
 */
@Entity
public class Notification implements Serializable, pl.mesayah.assistance.Entity {

    private static final String ENTITY_NAME = "notification";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * A title of this notification.
     */
    private String title;

    /**
     * Date and time this notification was sent.
     */
    private LocalDateTime sendDateTime;

    /**
     * Text in notification
     */
    private String text;

    /**
     * A type of this notification.
     */
    @Enumerated(EnumType.ORDINAL)
    private NotificationType type;

    @Enumerated(EnumType.ORDINAL)
    private NotificationDestination destination;

    private boolean readed;

    private String NotificationTarget;


    /**
     * Constructs notification object with no specified attributes.
     */
    public Notification() {

    }

    public NotificationDestination getDestination() {
        return destination;
    }

    public void setDestination(NotificationDestination destination) {
        this.destination = destination;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, sendDateTime, type);
    }

    public String getText() {
        return text;
    }

    public boolean isReaded() {
        return readed;
    }

    public void setReaded(boolean readed) {
        this.readed = readed;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(sendDateTime, that.sendDateTime) &&
                type == that.type;
    }


    @Override
    public Long getId() {


        return id;
    }


    @Override
    public String getEntityName() {

        return ENTITY_NAME;
    }


    public void setId(Long id) {

        this.id = id;
    }

    public Notification(String title, String text, NotificationType type, NotificationDestination destination, String notificationTarget) {
        this.title = title;
        this.text = text;
        this.type = type;
        this.destination = destination;
        this.sendDateTime = LocalDateTime.now();
        NotificationTarget = notificationTarget;
    }

    public LocalDateTime getSendDateTime() {

        return sendDateTime;
    }


    public void setSendDateTime(LocalDateTime sendDateTime) {

        this.sendDateTime = sendDateTime;
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


    /**
     * Describes a type of a notification.
     */
    public static enum NotificationType {

        INFO, ALERT, WARNING;
    }

    public String getNotificationTarget() {
        return NotificationTarget;
    }

    public void setNotificationTarget(String notificationTarget) {
        NotificationTarget = notificationTarget;
    }


}
