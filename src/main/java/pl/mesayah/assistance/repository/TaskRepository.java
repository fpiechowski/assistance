package pl.mesayah.assistance.repository;

import org.springframework.data.repository.CrudRepository;
import pl.mesayah.assistance.domain.Task;

/**
 * Repository for fetching and saving task in the database. It handles database communication.
 */
public interface TaskRepository extends CrudRepository<Task, Long> {

}
