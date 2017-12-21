package pl.mesayah.assistance.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mesayah.assistance.domain.Notification;
import pl.mesayah.assistance.repository.NotificationRepository;

import java.util.List;

/**
 * @author Filip Piechowski
 */

/**
 * Service for sending scheduled notifications.
 */
@Service
public class NotificationService {

    /**
     * Repository for fetching notifications from the database.
     */
    @Autowired
    private NotificationRepository notificationRepository;

    /**
     * Constructs default nofitication service with no parameters set.
     */
    public NotificationService() {

    }

    /**
     * Saves given notification in the database.
     *
     * @param notification a notification to save
     */
    public Notification save(Notification notification) {

        return notificationRepository.save(notification);
    }

    /**
     * Checks if notification with given ID exists in the repository.
     *
     * @param id ID of notification to look for
     * @return true if notification with given ID exists in the repository
     */
    public Boolean exists(Long id) {

        return notificationRepository.exists(id);
    }

    /**
     * Finds a notification with given ID.
     *
     * @param id an ID of notification to find
     */
    public Notification findById(Long id) {

        return notificationRepository.findOne(id);
    }

    public List<Notification> findByType(Notification.NotificationType type) {

        return notificationRepository.findByType(type);
    }

    /**
     * Deletes a notification with given ID.
     *
     * @param id an id of notification to delete
     */
    public void delete(Long id) {

        notificationRepository.delete(id);
    }

    /**
     * Deletes given notification.
     *
     * @param notification a notification to delete from the database
     */
    public void delete(Notification notification) {

        notificationRepository.delete(notification);
    }
}
