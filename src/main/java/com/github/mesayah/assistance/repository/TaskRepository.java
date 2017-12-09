package com.github.mesayah.assistance.repository;

import com.github.mesayah.assistance.model.Task;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for fetching and saving tasks in the database. It handles database communication.
 */
public interface TaskRepository extends CrudRepository<Task, Long> {
}
