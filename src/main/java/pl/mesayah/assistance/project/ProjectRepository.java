package pl.mesayah.assistance.project;

import org.springframework.data.repository.CrudRepository;
import pl.mesayah.assistance.task.Task;

import java.util.List;

/**
 * Repository for fetching and saving projects in the database. It handles database communication.
 */
public interface ProjectRepository extends CrudRepository<Project, Long> {

    List<Project> findAllByStatus(Task.Status status);
}
