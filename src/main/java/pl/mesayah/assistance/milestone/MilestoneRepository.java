package pl.mesayah.assistance.milestone;

import org.springframework.data.repository.CrudRepository;
import pl.mesayah.assistance.project.Project;

import java.util.List;

/**
 * @author Filip Piechowski
 */

/**
 * Repository for fetching and saving milestone in the database. It handles database communication.
 */
public interface MilestoneRepository extends CrudRepository<Milestone, Long> {

    /**
     * Finds all milestone defined for given project.
     *
     * @param project a project whose milestone to look for
     * @return a list of milestone defined for a given project
     */
    List<Milestone> findByProject(Project project);
}
