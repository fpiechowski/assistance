package pl.mesayah.assistance.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pl.mesayah.assistance.Entity;
import pl.mesayah.assistance.project.Project;
import pl.mesayah.assistance.project.ProjectRepository;

@Component
public class RepositoryUtils {

    private static ProjectRepository projectRepository;

    public static <T> CrudRepository getRepositoryFor(Class<? extends Entity> entity) {

        if (entity == Project.class) {

            return projectRepository;
        }
        return null;
    }

    @Autowired
    public void setProjectRepository(ProjectRepository projectRepository) {

        RepositoryUtils.projectRepository = projectRepository;
    }
}
