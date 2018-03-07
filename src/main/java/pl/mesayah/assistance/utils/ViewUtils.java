package pl.mesayah.assistance.utils;

import pl.mesayah.assistance.Entity;
import pl.mesayah.assistance.issue.Issue;
import pl.mesayah.assistance.issue.IssueDetailsView;
import pl.mesayah.assistance.issue.IssueListView;
import pl.mesayah.assistance.milestone.Milestone;
import pl.mesayah.assistance.milestone.MilestoneDetailsView;
import pl.mesayah.assistance.milestone.MilestoneListView;
import pl.mesayah.assistance.project.Project;
import pl.mesayah.assistance.project.ProjectDetailsView;
import pl.mesayah.assistance.project.ProjectListView;
import pl.mesayah.assistance.task.Task;
import pl.mesayah.assistance.task.TaskDetailsView;
import pl.mesayah.assistance.task.TaskListView;
import pl.mesayah.assistance.team.Team;
import pl.mesayah.assistance.team.TeamDetailsView;
import pl.mesayah.assistance.team.TeamListView;

public class ViewUtils {

    public static String getListViewNameFor(Class<? extends Entity> entity) {

        if (entity == Project.class) {
            return ProjectListView.VIEW_NAME;
        } else if (entity == Team.class) {
            return TeamListView.VIEW_NAME;
        } else if (entity == Issue.class) {
            return IssueListView.VIEW_NAME;
        } else if (entity == Task.class) {
            return TaskListView.VIEW_NAME;
        } else if (entity == Milestone.class) {
            return MilestoneListView.VIEW_NAME;
        }

        return null;
    }

    public static String getDetailsViewNameFor(Class<? extends Entity> entity) {

        if (entity == Project.class) {
            return ProjectDetailsView.VIEW_NAME;
        } else if (entity == Team.class) {
            return TeamDetailsView.VIEW_NAME;
        } else if (entity == Task.class) {
            return TaskDetailsView.VIEW_NAME;
        } else if (entity == Issue.class) {
            return IssueDetailsView.VIEW_NAME;
        } else if (entity == Milestone.class) {
            return MilestoneDetailsView.VIEW_NAME;
        }

        return null;
    }
}
