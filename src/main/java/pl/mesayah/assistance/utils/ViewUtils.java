package pl.mesayah.assistance.utils;

import org.springframework.security.access.method.P;
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
import pl.mesayah.assistance.ui.DetailsViews;
import pl.mesayah.assistance.ui.ListViews;

import java.util.Arrays;

public class ViewUtils {

    public static String getListViewNameFor(Class<? extends Entity> entity) {

        for (ListViews lv : ListViews.values()) {
            if (lv.getEntityClass() == entity) {
                return lv.getViewName();
            }
        }

        return null;
    }

    public static String getDetailsViewNameFor(Class<? extends Entity> entity) {

        for (DetailsViews dv : DetailsViews.values()) {
            if (dv.getEntityClass() == entity) {
                return dv.getViewName();
            }
        }

        return null;
    }
}
