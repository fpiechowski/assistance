package com.github.mesayah.assistance.repository;

import com.github.mesayah.assistance.model.Privilege;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for fetching and saving privileges in the database. It handles database communication.
 */
public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {
}
