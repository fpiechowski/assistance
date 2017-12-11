package pl.mesayah.assistance.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mesayah.assistance.domain.Project;
import pl.mesayah.assistance.domain.Status;
import pl.mesayah.assistance.repository.ProjectRepository;

import java.util.List;

/**
 * Service for managing projects.
 */
@Service
public class ProjectService {

    /**
     * Repository fetching projects from database.
     */
    @Autowired
    private ProjectRepository projectRepository;

    /**
     * Default constructor.
     */
    public ProjectService() {

    }

    /**
     * Saves a given project entity in database.
     *
     * @param chatMessage chat massage entity to save.
     */
    public void save(Project chatMessage) {

        projectRepository.save(chatMessage);
    }

    /**
     * Retrieves a project entity by its id.
     *
     * @param id given id
     * @return a project entity with the given id or null if none found.
     */
    public Project findById(Long id) {

        return projectRepository.findOne(id);
    }

    /**
     * Retrive project entities with the given status.
     *
     * @param status given status.
     * @return project entities with the given status or null if none found.
     */
    public List<Project> findAllByStatus(Status status) {

        return projectRepository.findAllByStatus(status);
    }

    /**
     * Deletes the project entity with the given id.
     *
     * @param id given id.
     */
    public void delete(Long id) {

        projectRepository.delete(id);
    }
}
