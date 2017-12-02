package com.github.mesayah.assistance.repository;

import com.github.mesayah.assistance.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
