package pl.mesayah.assistance.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pl.mesayah.assistance.Entity;
import pl.mesayah.assistance.issue.Issue;
import pl.mesayah.assistance.issue.IssueRepository;
import pl.mesayah.assistance.messaging.Channel;
import pl.mesayah.assistance.messaging.ChannelRepository;
import pl.mesayah.assistance.messaging.Message;
import pl.mesayah.assistance.messaging.MessageRepository;
import pl.mesayah.assistance.milestone.Milestone;
import pl.mesayah.assistance.milestone.MilestoneRepository;
import pl.mesayah.assistance.notification.Notification;
import pl.mesayah.assistance.notification.NotificationRepository;
import pl.mesayah.assistance.project.Project;
import pl.mesayah.assistance.project.ProjectRepository;
import pl.mesayah.assistance.security.Privilege;
import pl.mesayah.assistance.security.PrivilegeRepository;
import pl.mesayah.assistance.security.Role;
import pl.mesayah.assistance.security.RoleRepository;
import pl.mesayah.assistance.task.Task;
import pl.mesayah.assistance.task.TaskRepository;
import pl.mesayah.assistance.team.Team;
import pl.mesayah.assistance.team.TeamRepository;
import pl.mesayah.assistance.todo.PersonalNote;
import pl.mesayah.assistance.todo.PersonalNoteRepository;
import pl.mesayah.assistance.user.User;
import pl.mesayah.assistance.user.UserRepository;

@Component
public class RepositoryUtils {

    private static ProjectRepository projectRepository;

    private static TaskRepository taskRepository;

    private static IssueRepository issueRepository;

    private static ChannelRepository channelRepository;

    private static MessageRepository messageRepository;

    private static MilestoneRepository milestoneRepository;

    private static NotificationRepository notificationRepository;

    private static PrivilegeRepository privilegeRepository;

    private static RoleRepository roleRepository;

    private static TeamRepository teamRepository;

    private static PersonalNoteRepository personalNoteRepository;

    private static UserRepository userRepository;

    public static CrudRepository getRepositoryFor(Class<? extends Entity> entity) {


        if (entity == Project.class) {

            return projectRepository;
        } else if (entity == Task.class) {

            return taskRepository;
        } else if (entity == Team.class) {

            return teamRepository;
        } else if (entity == Message.class) {

            return messageRepository;
        } else if (entity == Channel.class) {

            return channelRepository;
        } else if (entity == Issue.class) {

            return issueRepository;
        } else if (entity == User.class) {

            return userRepository;
        } else if (entity == PersonalNote.class) {

            return personalNoteRepository;
        } else if (entity == Milestone.class) {

            return milestoneRepository;
        } else if (entity == Notification.class) {

            return notificationRepository;
        } else if (entity == Role.class) {

            return roleRepository;
        } else if (entity == Privilege.class) {

            return privilegeRepository;
        } else {
            return null;
        }
    }

    @Autowired
    public void setIssueRepository(IssueRepository issueRepository) {

        RepositoryUtils.issueRepository = issueRepository;
    }

    @Autowired
    public void setChannelRepository(ChannelRepository channelRepository) {

        RepositoryUtils.channelRepository = channelRepository;
    }

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository) {

        RepositoryUtils.messageRepository = messageRepository;
    }

    @Autowired
    public void setMilestoneRepository(MilestoneRepository milestoneRepository) {

        RepositoryUtils.milestoneRepository = milestoneRepository;
    }

    @Autowired
    public void setNotificationRepository(NotificationRepository notificationRepository) {

        RepositoryUtils.notificationRepository = notificationRepository;
    }

    @Autowired
    public void setPrivilegeRepository(PrivilegeRepository privilegeRepository) {

        RepositoryUtils.privilegeRepository = privilegeRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {

        RepositoryUtils.roleRepository = roleRepository;
    }

    @Autowired
    public void setTeamRepository(TeamRepository teamRepository) {

        RepositoryUtils.teamRepository = teamRepository;
    }

    @Autowired
    public void setPersonalNoteRepository(PersonalNoteRepository personalNoteRepository) {

        RepositoryUtils.personalNoteRepository = personalNoteRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {

        RepositoryUtils.userRepository = userRepository;
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
