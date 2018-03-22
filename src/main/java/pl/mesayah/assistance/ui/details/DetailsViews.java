package pl.mesayah.assistance.ui.details;

import pl.mesayah.assistance.Entity;
import pl.mesayah.assistance.issue.Issue;
import pl.mesayah.assistance.issue.IssueDetailsView;
import pl.mesayah.assistance.milestone.MilestoneDetailsView;
import pl.mesayah.assistance.project.Project;
import pl.mesayah.assistance.project.ProjectDetailsView;
import pl.mesayah.assistance.task.Task;
import pl.mesayah.assistance.task.TaskDetailsView;
import pl.mesayah.assistance.team.Team;
import pl.mesayah.assistance.team.TeamDetailsView;
import pl.mesayah.assistance.user.User;
import pl.mesayah.assistance.user.UserDetailsView;

public enum DetailsViews {
    PROJECT(Project.class, ProjectDetailsView.VIEW_NAME),
    TEAM(Team.class, TeamDetailsView.VIEW_NAME),
    ISSUE(Issue.class, IssueDetailsView.VIEW_NAME),
    TASK(Task.class, TaskDetailsView.VIEW_NAME),
    MILESTONE(Task.class, MilestoneDetailsView.VIEW_NAME),
    USER(User.class, UserDetailsView.VIEW_NAME);

    private Class<? extends Entity> entityClass;
    private String viewName;


    DetailsViews(Class<? extends Entity> entityClass, String viewName) {

        this.entityClass = entityClass;
        this.viewName = viewName;

    }


    public static String getDetailsViewNameFor(Class<? extends Entity> entity) {

        for (DetailsViews dv : values()) {
            if (dv.getEntityClass() == entity) {
                return dv.getViewName();
            }
        }

        return null;
    }


    public Class<? extends Entity> getEntityClass() {

        return entityClass;
    }


    public String getViewName() {

        return viewName;
    }
}
