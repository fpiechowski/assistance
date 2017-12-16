package pl.mesayah.assistance.project.task;

// TODO: Implement this.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mesayah.assistance.domain.Project;
import pl.mesayah.assistance.domain.Task;
import pl.mesayah.assistance.repository.TaskRepository;

import java.util.List;

/**
 * @author Lukasz Jaworski
 *
 * Service for managing tasks.
 */
@Service
public class TaskService {

    /**
     * Repository for fetching tasks from the database.
     */
    @Autowired
    private TaskRepository taskRepository;

    /**
     * Constructs a default milestone service. Needed for JPA to work.
     */
    public TaskService() {

    }

    /**
     * Saves given task in the database.
     *
     * @param task a task to save
     */
    public void save(Task task) {

        taskRepository.save(task);
    }

    /**
     * Finds task by unique identifier.
     *
     * @param id an unique identifier of the project looked for
     * @return a task with given ID or null if not found
     */
    public Task findById(Long id) {

        return taskRepository.findOne(id);
    }

    /**
     * Finds all tasks for given project.
     *
     * @param project a project whose milestone we look for
     * @return all tasks of given project
     */
    public List<Task> findByProject(Project project) {

        return taskRepository.findByProject(project);
    }

    /**
     * Deletes task with given ID.
     *
     * @param id an id of the milestone to delete
     */
    public void delete(Long id) {

        taskRepository.delete(id);
    }

    /**
     * Deletes given milestone from the database.
     *
     * @param task a task to delete
     */
    public void delete(Task task) {

        taskRepository.delete(task);
    }
}
