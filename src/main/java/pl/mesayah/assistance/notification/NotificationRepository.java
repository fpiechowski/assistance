package pl.mesayah.assistance.notification;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.mesayah.assistance.project.Project;

import java.util.List;
import java.util.stream.Stream;

/**
 * Repository for fetching and saving notifications in the database. It handles database communication.
 */
public interface NotificationRepository extends CrudRepository<Notification, Long> {

    List<Notification> findByType(Notification.NotificationType type);

    List<Notification> findAllByDestinationIs(NotificationDestination destination);

}
