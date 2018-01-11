package pl.mesayah.assistance.utils;

import pl.mesayah.assistance.project.Project;
import pl.mesayah.assistance.project.ProjectDetailsView;
import pl.mesayah.assistance.project.ProjectListView;

public class ViewUtils {

    public static <T> String getListViewNameFor(T entity) {

        if (entity instanceof Project) {
            return ProjectListView.VIEW_NAME;
        }

        return null;
    }

    public static <T> String getDetailsViewNameFor(T entity) {

        if (entity instanceof Project) {
            return ProjectDetailsView.VIEW_NAME;
        }

        return null;
    }
}
