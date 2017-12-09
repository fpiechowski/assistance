package com.github.mesayah.assistance.repository;

import com.github.mesayah.assistance.model.Milestone;
import com.github.mesayah.assistance.model.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Filip Piechowski
 */

/**
 * Repository for fetching and saving milestones in the database. It handles database communication.
 */
public interface MilestoneRepository extends CrudRepository<Milestone, Long> {

    /**
     * Finds all milestones defined for given project.
     *
     * @param project a project whose milestones to look for
     * @return a list of milestones defined for a given project
     */
    List<Milestone> findByProject(Project project);
}
