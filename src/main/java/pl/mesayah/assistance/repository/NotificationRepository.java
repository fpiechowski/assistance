package pl.mesayah.assistance.repository;

import org.springframework.data.repository.CrudRepository;
import pl.mesayah.assistance.domain.Notification;

import java.util.List;

/**
 * Repository for fetching and saving notifications in the database. It handles database communication.
 */
public interface NotificationRepository extends CrudRepository<Notification, Long> {

    List<Notification> findByType(Notification.NotificationType type);


}
