package pl.mesayah.assistance.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pl.mesayah.assistance.Entity;
import pl.mesayah.assistance.project.Project;
import pl.mesayah.assistance.project.ProjectRepository;
import pl.mesayah.assistance.task.Task;
import pl.mesayah.assistance.task.TaskRepository;

@Component
public class RepositoryUtils {

    private static ProjectRepository projectRepository;

    private static TaskRepository taskRepository;

    public static CrudRepository getRepositoryFor(Class<? extends Entity> entity) {

        if (entity == Project.class) {

            return projectRepository;
        } else if (entity == Task.class) {

            return taskRepository;
        }
        return null;
    }

    @Autowired
    public void setProjectRepository(ProjectRepository projectRepository) {

        RepositoryUtils.projectRepository = projectRepository;
    }

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {

        RepositoryUtils.taskRepository = taskRepository;
    }
}
