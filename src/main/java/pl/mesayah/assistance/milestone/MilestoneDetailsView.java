package pl.mesayah.assistance.milestone;

import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mesayah.assistance.project.Project;
import pl.mesayah.assistance.project.ProjectService;
import pl.mesayah.assistance.task.Task;
import pl.mesayah.assistance.task.TaskService;
import pl.mesayah.assistance.ui.details.AbstractDetailsView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SpringView(name = MilestoneDetailsView.VIEW_NAME)
public class MilestoneDetailsView extends AbstractDetailsView<Milestone> {

    /**
     * A view name used to navigate between views.
     */

    public static final String VIEW_NAME = "milestone";

    @Autowired
    ProjectService projectService;
    @Autowired
    TaskService taskService;


    /**
     * A label showing a title of the milestone.
     */
    private Label nameLabel;

    /**
     * A label showing a title of the TwinCol.
     */
    private Label taskLabel;

    /**
     * A label showing a title of the project.
     */
    private Label projectLabel;

    /**
     * A label showing a title of the channel.
     */
    private Label channelLabel;

    /**
     * A Text Field to edit a title of the project.
     */
    private TextField nameTextField;
    /**
     * A ComboBox for selecting project.
     */
    private ComboBox<Project> projectComboBox;
    /**
     * A Twin Column Selector for selecting tasks.
     */
    private TwinColSelect<Task> taskTwinColSelect;
    /**
     * A box for input deadline date.
     */
    private DateField deadlineBox;
    @Override
    protected List<Component> initializeEditComponents() {

        HorizontalLayout container = new HorizontalLayout();
        VerticalLayout secondContainer = new VerticalLayout();
        container.setSizeUndefined();
        container.setSpacing(false);
        container.setMargin(false);

        nameTextField = new TextField("Milestone name:");
        nameTextField.setWidth("100%");
        nameTextField.setRequiredIndicatorVisible(true);

        taskTwinColSelect = new TwinColSelect<>("Choose task for milestone");
        taskTwinColSelect.setItemCaptionGenerator((ItemCaptionGenerator<Task>) task -> task.getName());

        projectComboBox = new ComboBox<>("Project");
        projectComboBox.setEmptySelectionAllowed(false);
        projectComboBox.setItemCaptionGenerator((ItemCaptionGenerator<Project>) project -> project.getName());
        projectComboBox.setRequiredIndicatorVisible(true);

        deadlineBox = new DateField();
        deadlineBox.setRequiredIndicatorVisible(true);

        secondContainer.addComponents(projectComboBox,deadlineBox);

        container.addComponents(taskTwinColSelect, secondContainer);

        return new ArrayList<>(Arrays.asList(
                nameTextField,
                container
        ));
    }


    @Override
    protected Button initializeDeleteButton() {

        return new Button("Delete", VaadinIcons.TRASH);
    }


    @Override
    protected Button initializeConfirmButton() {

        return new Button("Confirm", VaadinIcons.CHECK);
    }


    @Override
    protected Button initializeEditButton() {

        return new Button("Edit", VaadinIcons.PENCIL);
    }


    @Override
    protected Binder<Milestone> initializeDataBinder() {

        Binder<Milestone> dataBinder = new Binder<>(Milestone.class);

        dataBinder.forField(nameTextField)
                .withValidator(name -> name.length() >= 3, "Name must be at least 3 characters long.")
                .withValidator(name -> name.length() < 21, "Name is too long! (Max 20)")
                .withValidator(name -> name.equals(name.trim()), "Name containing white spaces!")
                .bind(Milestone::getName, Milestone::setName);
        dataBinder.forField(taskTwinColSelect)
                .bind(Milestone::getTasks, Milestone::setTasks);
        dataBinder.forField(projectComboBox)
                .bind(Milestone::getProject, Milestone::setProject);
        dataBinder.forField(deadlineBox)
                .bind(Milestone::getDeadline, Milestone::setDeadline);
        return dataBinder;
    }


    @Override
    protected void loadData() {

        Collection<Task> tasks = taskService.findAll();
        taskTwinColSelect.setItems(tasks);
        Collection<Project> projects = projectService.findAll();
        projectComboBox.setItems(projects);
    }


    @Override
    protected Milestone createEmptyEntity() {

        Milestone empty = new Milestone();

        return empty;
    }


}
