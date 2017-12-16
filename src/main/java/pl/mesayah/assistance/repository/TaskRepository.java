package pl.mesayah.assistance.repository;

import org.springframework.data.repository.CrudRepository;
import pl.mesayah.assistance.domain.Project;
import pl.mesayah.assistance.domain.Task;

import java.util.List;

/**
 * Repository for fetching and saving task in the database. It handles database communication.
 */
public interface TaskRepository extends CrudRepository<Task, Long> {

    /**
     * Finds all tasks defined for given project.
     *
     * @param project a project whose task to look for
     * @return a list of tasks defined for a given project
     */

    List<Task> findByProject(Project project);
}
