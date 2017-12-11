package com.github.mesayah.assistance.repository;

import org.springframework.data.repository.CrudRepository;
import pl.mesayah.assistance.domain.Project;
import pl.mesayah.assistance.domain.Status;

import java.util.List;

/**
 * Repository for fetching and saving projects in the database. It handles database communication.
 */
public interface ProjectRepository extends CrudRepository<Project, Long> {
    List<Project> findAllByStatus(Status status);
}
