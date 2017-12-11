package pl.mesayah.assistance.repository;

import org.springframework.data.repository.CrudRepository;
import pl.mesayah.assistance.domain.Team;

/**
 * Repository for fetching and saving teams in the database. It handles database communication.
 */
public interface TeamRepository extends CrudRepository<Team, Long> {

}
