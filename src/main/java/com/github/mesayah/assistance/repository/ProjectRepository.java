package com.github.mesayah.assistance.repository;

import com.github.mesayah.assistance.model.Project;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for fetching and saving projects in the database. It handles database communication.
 */
public interface ProjectRepository extends CrudRepository<Project, Long> {
}
