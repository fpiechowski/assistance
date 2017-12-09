package com.github.mesayah.assistance.repository;

import com.github.mesayah.assistance.model.Notification;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositofy for fetching notifications from the database.
 */
public interface NotificationRepository extends CrudRepository<Notification, Long> {


}
