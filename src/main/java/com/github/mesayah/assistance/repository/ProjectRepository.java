package com.github.mesayah.assistance.repository;

import com.github.mesayah.assistance.model.Project;
import com.github.mesayah.assistance.model.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository for fetching and saving projects in the database. It handles database communication.
 */
public interface ProjectRepository extends CrudRepository<Project, Long> {
    List<Project> findAllByStatus(Status status);
}
