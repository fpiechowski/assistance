package pl.mesayah.assistance.repository;

import org.springframework.data.repository.CrudRepository;
import pl.mesayah.assistance.domain.Project;

/**
 * Repository for fetching and saving project in the database. It handles database communication.
 */
public interface ProjectRepository extends CrudRepository<Project, Long> {

}
