package com.github.mesayah.assistance.model;

import javax.persistence.Entity;

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
     * Constructs default notification entity with no specified parameters.
     */
    public Notification() {

    }
}
