package pl.mesayah.assistance.utils;

import pl.mesayah.assistance.Entity;
import pl.mesayah.assistance.project.Project;
import pl.mesayah.assistance.project.ProjectDetailsView;
import pl.mesayah.assistance.project.ProjectListView;
import pl.mesayah.assistance.team.Team;
import pl.mesayah.assistance.team.TeamDetailsView;

public class ViewUtils {

    public static String getListViewNameFor(Class<? extends Entity> entity) {

        if (entity == Project.class) {
            return ProjectListView.VIEW_NAME;
        }

        return null;
    }

    public static <T> String getDetailsViewNameFor(Class<? extends Entity> entity) {

        if (entity == Project.class) {
            return ProjectDetailsView.VIEW_NAME;
        } else if (entity == Team.class) {
            return TeamDetailsView.VIEW_NAME;
        }

        return null;
    }
}
