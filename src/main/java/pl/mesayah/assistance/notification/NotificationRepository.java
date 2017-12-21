package pl.mesayah.assistance.notification;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository for fetching and saving notifications in the database. It handles database communication.
 */
public interface NotificationRepository extends CrudRepository<Notification, Long> {

    List<Notification> findByType(Notification.NotificationType type);


}
