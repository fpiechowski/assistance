package pl.mesayah.assistance.task;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.grid.ColumnResizeMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import org.springframework.beans.factory.annotation.Autowired;
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
        grid.setColumns("id", "name", "status", "priority", "deadline", "type", "project");
        grid.setColumnResizeMode(ColumnResizeMode.ANIMATED);
        return grid;
    }


    @Override
    protected boolean isGridEditable() {

        return false;
    }


    @Override
    protected Button initializeDetailsButton() {

        return new Button("Details", VaadinIcons.EYE);
    }


    @Override
    protected Button initializeNewButton() {

        return new Button("New", VaadinIcons.PLUS);
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
    public Collection<Task> fetchDataSet() {

        return taskService.findAll();
    }
}
