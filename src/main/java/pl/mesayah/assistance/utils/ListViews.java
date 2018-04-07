package pl.mesayah.assistance.utils;

import pl.mesayah.assistance.Entity;
import pl.mesayah.assistance.issue.Issue;
import pl.mesayah.assistance.issue.IssueListView;
import pl.mesayah.assistance.milestone.Milestone;
import pl.mesayah.assistance.milestone.MilestoneListView;
import pl.mesayah.assistance.project.Project;
import pl.mesayah.assistance.project.ProjectListView;
import pl.mesayah.assistance.task.Task;
import pl.mesayah.assistance.task.TaskListView;
import pl.mesayah.assistance.team.Team;
import pl.mesayah.assistance.team.TeamListView;
import pl.mesayah.assistance.user.User;
import pl.mesayah.assistance.user.UserListView;


public enum ListViews {
    PROJECT(Project.class, ProjectListView.VIEW_NAME),
    TEAM(Team.class, TeamListView.VIEW_NAME),
    ISSUE(Issue.class, IssueListView.VIEW_NAME),
    TASK(Task.class, TaskListView.VIEW_NAME),
    MILESTONE(Milestone.class, MilestoneListView.VIEW_NAME),
    USER(User.class, UserListView.VIEW_NAME);

    private Class<? extends Entity> entityClass;
    private String viewName;


    ListViews(Class<? extends Entity> entityClass, String viewName) {

        this.entityClass = entityClass;
        this.viewName = viewName;

    }


    public static String getListViewNameFor(Class<? extends Entity> entity) {

        for (ListViews lv : values()) {
            if (lv.getEntityClass() == entity) {
                return lv.getViewName();
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
