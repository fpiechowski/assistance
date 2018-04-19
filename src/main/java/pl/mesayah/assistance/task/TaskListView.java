package pl.mesayah.assistance.task;

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

@SpringView(name = TaskListView.VIEW_NAME)
public class TaskListView extends AbstractListView<Task> {

    public static final String VIEW_NAME = "tasks";

    @Autowired
    private TaskService taskService;


    @Override
    protected Task createEmptyEntity() {

        return new Task();
    }


    @Override
    protected List<Button> initializeAdditionalButtons() {

        return null;
    }


    @Override
    public Grid<Task> initializeListing() {

        Grid<Task> grid = new Grid<>(Task.class);
        grid.setSizeFull();
        grid.setColumns("id", "name", "status", "priority", "deadline", "type", "project", "assignedUsers");
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
        return detailsButton;
    }


    @Override
    protected Button initializeNewButton() {

        Button newButton = new Button("New", VaadinIcons.PLUS);
        if(SecurityUtils.hasRole(Role.CLIENT) || SecurityUtils.hasRole(Role.PROJECT_MANAGER) ) {
            newButton.setEnabled(false);
        }
        return newButton;
    }


    @Override
    protected Button initializeEditButton() {


        Button editButton = new Button("Edit", VaadinIcons.PENCIL);
        if(SecurityUtils.hasRole(Role.CLIENT) || SecurityUtils.hasRole(Role.PROJECT_MANAGER)) {
            editButton.setEnabled(false);
        }
        return editButton;
    }


    @Override
    protected Button initializeDeleteButton() {


        Button deleteButton = new Button("Delete", VaadinIcons.TRASH);
        if(SecurityUtils.hasRole(Role.CLIENT) || SecurityUtils.hasRole(Role.PROJECT_MANAGER)) {
            deleteButton.setEnabled(false);
        }
        return deleteButton;

    }


    @Override
    public Collection<Task> fetchDataSet() {

        return taskService.findAll();
    }
}
