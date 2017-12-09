package com.github.mesayah.assistance.repository;

import com.github.mesayah.assistance.model.PersonalTask;
import com.github.mesayah.assistance.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Filip Piechowski
 */

/**
 * Repository for fetching personal tasks from database. It handles database communication and provides specified
 * methods for custom queries.
 */
@Repository
public interface PersonalTaskRepository extends CrudRepository<PersonalTask, Long> {

    /**
     * Finds and returns all personal tasks owned by a given user.
     *
     * @param owner a user whose personal tasks to look for
     * @return a list of all personal tasks owned by a given user
     */
    List<PersonalTask> findByOwner(User owner);
}
