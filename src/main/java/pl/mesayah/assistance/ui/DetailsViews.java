package pl.mesayah.assistance.ui;

import pl.mesayah.assistance.Entity;
import pl.mesayah.assistance.issue.Issue;
import pl.mesayah.assistance.issue.IssueDetailsView;
import pl.mesayah.assistance.issue.IssueListView;
import pl.mesayah.assistance.project.Project;
import pl.mesayah.assistance.project.ProjectDetailsView;
import pl.mesayah.assistance.project.ProjectListView;
import pl.mesayah.assistance.task.Task;
import pl.mesayah.assistance.task.TaskDetailsView;
import pl.mesayah.assistance.task.TaskListView;
import pl.mesayah.assistance.team.Team;
import pl.mesayah.assistance.team.TeamDetailsView;
import pl.mesayah.assistance.team.TeamListView;

public enum DetailsViews {
    PROJECT(Project.class, ProjectDetailsView.VIEW_NAME),
    TEAM(Team.class, TeamDetailsView.VIEW_NAME),
    ISSUE(Issue.class, IssueDetailsView.VIEW_NAME),
    TASK(Task.class, TaskDetailsView.VIEW_NAME);

    private Class<? extends Entity> entityClass;
    private String viewName;

    public String getViewName() {
        return viewName;
    }

    DetailsViews(Class<? extends Entity> entityClass, String viewName) {
        this.entityClass = entityClass;
        this.viewName = viewName;

    }

    public Class<? extends Entity> getEntityClass() {
        return entityClass;
    }
}
