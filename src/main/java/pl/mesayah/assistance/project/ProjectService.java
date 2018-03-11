package pl.mesayah.assistance.project;

import com.vaadin.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing projects.
 */
@Service
@SpringComponent
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
    public Project save(Project chatMessage) {

        return projectRepository.save(chatMessage);
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
     * @param phase given status.
     * @return project entities with the given status or null if none found.
     */
    public List<Project> findAllByStatus(Project.Phase phase) {

        return projectRepository.findAllByPhase(phase);
    }


    /**
     * Finds all existing projects.
     *
     * @return list of all existing projects
     */
    public List<Project> findAll() {

        return (List<Project>) projectRepository.findAll();
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
