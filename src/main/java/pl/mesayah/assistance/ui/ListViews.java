package pl.mesayah.assistance.ui;

import org.apache.commons.lang3.StringUtils;
import pl.mesayah.assistance.Entity;
import pl.mesayah.assistance.issue.Issue;
import pl.mesayah.assistance.issue.IssueListView;
import pl.mesayah.assistance.project.Project;
import pl.mesayah.assistance.project.ProjectListView;
import pl.mesayah.assistance.task.Task;
import pl.mesayah.assistance.task.TaskListView;
import pl.mesayah.assistance.team.Team;
import pl.mesayah.assistance.team.TeamListView;


public enum ListViews {
    PROJECT(Project.class, ProjectListView.VIEW_NAME),
    TEAM(Team.class, TeamListView.VIEW_NAME),
    ISSUE(Issue.class, IssueListView.VIEW_NAME),
    TASK(Task.class, TaskListView.VIEW_NAME);

    private Class<? extends Entity> entityClass;
    private String viewName;

    public String getViewName() {
        return viewName;
    }

    ListViews(Class<? extends Entity> entityClass, String viewName) {
        this.entityClass = entityClass;
        this.viewName = viewName;

    }

    public Class<? extends Entity> getEntityClass() {
        return entityClass;
    }
}
