package pl.mesayah.assistance.team;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository for fetching and saving teams in the database. It handles database communication.
 */
public interface TeamRepository extends CrudRepository<Team, Long> {

    /**
     * Finds all teams defined for given name.
     *
     * @param name a name of team to look for
     * @return a list of teams defined for a given name
     */

    Team findByName(String name);

}
