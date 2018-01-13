package pl.mesayah.assistance.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mesayah.assistance.project.Project;

import java.util.List;

/**
 * @author Lukasz Jaworski
 * <p>
 * Service for managing teams.
 */
@Service
public class TeamService {

    /**
     * Repository for fetching teams from the database.
     */
    @Autowired
    private TeamRepository teamRepository;

    /**
     * Constructs a default team service. Needed for JPA to work.
     */
    public TeamService() {

    }

    /**
     * Saves given task in the database.
     *
     * @param team a task to save
     */
    public void save(Team team) {

        teamRepository.save(team);
    }

    /**
     * Finds team by unique identifier.
     *
     * @param id an unique identifier of the project looked for
     * @return a task with given ID or null if not found
     */
    public Team findById(Long id) {

        return teamRepository.findOne(id);
    }

    /**
     * Finds all teams for given project.
     *
     * @param name a project whose team we look for
     * @return all teams of given project
     */
    public Team findByName(String name) {

        return teamRepository.findByName(name);
    }

    /**
     * Deletes team with given ID.
     *
     * @param id an id of the team to delete
     */
    public void delete(Long id) {

        teamRepository.delete(id);
    }

    /**
     * Deletes given team from the database.
     *
     * @param team a team to delete
     */
    public void delete(Team team) {

        teamRepository.delete(team);
    }


}
