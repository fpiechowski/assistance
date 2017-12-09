package com.github.mesayah.assistance.repository;

import com.github.mesayah.assistance.model.Notification;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for fetching and saving notifications in the database. It handles database communication.
 */
public interface NotificationRepository extends CrudRepository<Notification, Long> {


}
