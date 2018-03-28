package pl.mesayah.assistance.project;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository for fetching and saving projects in the database. It handles database communication.
 */
public interface ProjectRepository extends CrudRepository<Project, Long> {

    List<Project> findAllByPhase(Project.Phase phase);

    @Query(value = "SELECT Project.name FROM Project project WHERE project.name > :id", nativeQuery = true)
    String existsByName (String name);
}
