package pl.mesayah.assistance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pl.mesayah.assistance.issue.Issue;
import pl.mesayah.assistance.issue.IssueRepository;
import pl.mesayah.assistance.milestone.Milestone;
import pl.mesayah.assistance.milestone.MilestoneRepository;
import pl.mesayah.assistance.notification.Notification;
import pl.mesayah.assistance.notification.NotificationRepository;
import pl.mesayah.assistance.project.Project;
import pl.mesayah.assistance.project.ProjectRepository;
import pl.mesayah.assistance.security.privilege.Privilege;
import pl.mesayah.assistance.security.privilege.PrivilegeRepository;
import pl.mesayah.assistance.security.role.Role;
import pl.mesayah.assistance.security.role.RoleRepository;
import pl.mesayah.assistance.task.Task;
import pl.mesayah.assistance.task.TaskRepository;
import pl.mesayah.assistance.team.Team;
import pl.mesayah.assistance.team.TeamRepository;
import pl.mesayah.assistance.user.User;
import pl.mesayah.assistance.user.UserRepository;

import java.util.HashMap;
import java.util.Map;

@Component
public class Repositories {


    private static Map<Class<? extends Entity>, CrudRepository> repositories = new HashMap<>();


    @Autowired
    public Repositories(
            ProjectRepository projectRepository,
            TaskRepository taskRepository,
            IssueRepository issueRepository,
            MilestoneRepository milestoneRepository,
            NotificationRepository notificationRepository,
            PrivilegeRepository privilegeRepository,
            RoleRepository roleRepository,
            TeamRepository teamRepository,
            UserRepository userRepository
    ) {

        repositories.put(Project.class, projectRepository);
        repositories.put(Task.class, taskRepository);
        repositories.put(Issue.class, issueRepository);
        repositories.put(Milestone.class, milestoneRepository);
        repositories.put(Notification.class, notificationRepository);
        repositories.put(Privilege.class, privilegeRepository);
        repositories.put(Role.class, roleRepository);
        repositories.put(Team.class, teamRepository);
        repositories.put(User.class, userRepository);
    }


    public static CrudRepository getRepositoryFor(Class<? extends Entity> entity) {

        CrudRepository repo = repositories.get(entity);
        return repositories.get(entity);
    }
}
