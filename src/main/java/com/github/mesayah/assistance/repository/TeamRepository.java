package com.github.mesayah.assistance.repository;

import com.github.mesayah.assistance.model.Team;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for fetching and saving teams in the database. It handles database communication.
 */
public interface TeamRepository extends CrudRepository<Team, Long> {
}
