package pl.mesayah.assistance.project;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.grid.ColumnResizeMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mesayah.assistance.ui.AbstractListView;

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
    protected Button initializeNewButton() {

        return new Button("New", VaadinIcons.PLUS);
    }

    @Override
    protected List<Button> initializeAdditionalButtons() {

        return null;
    }

    @Override
    protected Button initializeDetailsButton() {

        return new Button("Details", VaadinIcons.EYE);
    }

    @Override
    public Collection<Project> fetchDataSet() {

        return projectService.findAll();
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
    protected Button initializeEditButton() {

        return new Button("Edit", VaadinIcons.PENCIL);
    }

    @Override
    protected Button initializeDeleteButton() {

        return new Button("Delete", VaadinIcons.TRASH);
    }

    @Override
    protected boolean isGridEditable() {

        return false;
    }
}
