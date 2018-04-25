package pl.mesayah.assistance.project;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.grid.ColumnResizeMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mesayah.assistance.security.SecurityUtils;
import pl.mesayah.assistance.security.role.Role;
import pl.mesayah.assistance.ui.list.AbstractListView;

import java.util.Collection;
import java.util.List;

/**
 * A view containing list of all projects created in the application.
 */
@SpringView(name = ProjectListView.VIEW_NAME)
public class ProjectListView extends AbstractListView<Project> {

    public static final String VIEW_NAME = "projects";

    @Autowired
    private ProjectService projectService;


    @Override
    protected Project createEmptyEntity() {

        return new Project();
    }


    @Override
    protected List<Button> initializeAdditionalButtons() {

        return null;
    }


    @Override
    public Grid<Project> initializeListing() {

        Grid<Project> grid = new Grid<>(Project.class);
        grid.setSizeFull();
        grid.setColumns("id", "name", "phase", "startDate", "deadline", "manager");
        grid.setColumnResizeMode(ColumnResizeMode.ANIMATED);
        return grid;
    }


    @Override
    protected boolean isGridEditable() {

        return false;
    }


    @Override
    protected Button initializeDetailsButton() {

        Button detailsButton = new Button("Details", VaadinIcons.EYE);
        if(!SecurityUtils.hasRole(Role.SUPER_ADMIN) && !SecurityUtils.hasRole(Role.PROJECT_MANAGER)) {
            detailsButton.setEnabled(false);
            detailsButton.setDescription("t");
        }
        return detailsButton;
    }



    @Override
    protected Button initializeNewButton() {

        Button newButton = new Button("New", VaadinIcons.PLUS);
        if(!SecurityUtils.hasRole(Role.SUPER_ADMIN) && !SecurityUtils.hasRole(Role.PROJECT_MANAGER)) {
            newButton.setEnabled(false);
            newButton.setDescription("t");
        }
        return newButton;
    }


    @Override
    protected Button initializeEditButton() {


        Button editButton = new Button("Edit", VaadinIcons.PENCIL);
        if(!SecurityUtils.hasRole(Role.SUPER_ADMIN) && !SecurityUtils.hasRole(Role.PROJECT_MANAGER)) {
            editButton.setEnabled(false);
            editButton.setDescription("t");
        }
        return editButton;
    }


    @Override
    protected Button initializeDeleteButton() {


        Button deleteButton = new Button("Delete", VaadinIcons.TRASH);
        if(!SecurityUtils.hasRole(Role.SUPER_ADMIN) && !SecurityUtils.hasRole(Role.PROJECT_MANAGER)) {
            deleteButton.setEnabled(false);
            deleteButton.setDescription("t");
        }
        return deleteButton;

    }


    @Override
    public Collection<Project> fetchDataSet() {

        return projectService.findAll();
    }
}
