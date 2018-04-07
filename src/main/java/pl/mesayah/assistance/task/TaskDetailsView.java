package pl.mesayah.assistance.task;

import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mesayah.assistance.project.Project;
import pl.mesayah.assistance.project.ProjectService;
import pl.mesayah.assistance.ui.details.AbstractDetailsView;
import pl.mesayah.assistance.user.User;
import pl.mesayah.assistance.user.UserService;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * A view of task details. It presents parameters of a given task.
 */
@SpringView(name = TaskDetailsView.VIEW_NAME)
public class TaskDetailsView extends AbstractDetailsView<Task> {

    /**
     * A view name used to navigate between views.
     */
    public static final String VIEW_NAME = "task";



    /**
     * A Text Field to edit a title of the task.
     */
    private TextField nameTextField;


    /**
     * A drop down menu to select a status of the task.
     */
    private NativeSelect<Task.Status> statusNativeSelect;

    /**
     * A drop down menu to select a priority of the task.
     */
    private TwinColSelect<User> assignedUsersListBuilder;

    /**
     * A drop down menu to select a priority of the task.
     */
    private NativeSelect<Task.Priority> priorityNativeSelect;

    /**
     * A combo box for selecting task channel.
     */
    private ComboBox<Task> parentTaskComboBox;

    /**
     * A list builder for selecting task subtasks.
     */
    private TwinColSelect<Task> subtasksListBuilder;

    /**
     * A drop down menu to select a type of the task.
     */
    private NativeSelect<Task.Type> typeNativeSelect;

    /**
     * A combo box for selecting task project.
     */
    private ComboBox<Project> projectComboBox;


    /**
     * A text area for editing task's description.
     */
    private TextArea descriptionTextArea;


    /**
     * A date field for selecting task's deadline date.
     */
    private DateField deadlineDateField;

    /**
     * A unified formatter for start and deadline dates.
     */
    private DateTimeFormatter dateTimeFormatter;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaskService taskService;


    /**
     * Constructs a view in a read mode and initializes controls for it.
     */
    public TaskDetailsView() {

        dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd yyyyy");
    }



    @Override
    protected List<Component> initializeEditComponents() {


        nameTextField = new TextField("Name:");
        nameTextField.setWidth("100%");
        nameTextField.setRequiredIndicatorVisible(true);

        statusNativeSelect = new NativeSelect<>("Status:",
                Arrays.asList(Task.Status.values()));
        statusNativeSelect.setEmptySelectionAllowed(false);
        statusNativeSelect.setSelectedItem(Task.Status.WAITING);
        statusNativeSelect.setWidth("120px");
        statusNativeSelect.setRequiredIndicatorVisible(true);

        priorityNativeSelect = new NativeSelect<>("Priority:",
                Arrays.asList(Task.Priority.values()));
        priorityNativeSelect.setEmptySelectionAllowed(false);
        priorityNativeSelect.setSelectedItem(Task.Priority.MEDIUM);
        priorityNativeSelect.setWidth("100%");
        priorityNativeSelect.setRequiredIndicatorVisible(true);

        typeNativeSelect = new NativeSelect<>("Type:",
                Arrays.asList(Task.Type.values()));
        typeNativeSelect.setEmptySelectionAllowed(false);
        typeNativeSelect.setSelectedItem(null);
        typeNativeSelect.setWidth("100%");
        typeNativeSelect.setRequiredIndicatorVisible(true);


        descriptionTextArea = new TextArea("Description:");
        descriptionTextArea.setWidth("100%");
        descriptionTextArea.setHeight("100%");

        deadlineDateField = new DateField("Deadline:");
        deadlineDateField.setWidth("100%");

        parentTaskComboBox = new ComboBox<>("Parent Task");
        parentTaskComboBox.setEmptySelectionAllowed(false);
        parentTaskComboBox.setItemCaptionGenerator((ItemCaptionGenerator<Task>) task -> task.getId() + ' ' + task.getName());
        parentTaskComboBox.setRequiredIndicatorVisible(false);

        subtasksListBuilder = new TwinColSelect<>("Subtasks");
        subtasksListBuilder.setRows(6);
        subtasksListBuilder.setLeftColumnCaption("Available subtasks");
        subtasksListBuilder.setRightColumnCaption("Selected subtasks");
        subtasksListBuilder.setItemCaptionGenerator((ItemCaptionGenerator<Task>) task -> task.getId() + ' ' + task.getName());
        subtasksListBuilder.setWidth("90%");
        subtasksListBuilder.setHeight("100%");

        assignedUsersListBuilder = new TwinColSelect<>("Assigned to:");
        assignedUsersListBuilder.setRows(6);
        assignedUsersListBuilder.setLeftColumnCaption("Available users");
        assignedUsersListBuilder.setRightColumnCaption("Selected users");
        assignedUsersListBuilder.setItemCaptionGenerator((ItemCaptionGenerator<User>) user -> user.getUsername());
        assignedUsersListBuilder.setWidth("90%");
        assignedUsersListBuilder.setHeight("100%");

        projectComboBox = new ComboBox<>("Project");
        projectComboBox.setEmptySelectionAllowed(false);
        projectComboBox.setItemCaptionGenerator((ItemCaptionGenerator<Project>) project -> project.getId() + ' ' + project.getName());
        projectComboBox.setRequiredIndicatorVisible(false);

        VerticalLayout leftSideLayout = getLeftSide();
        VerticalLayout rightSideLayout = getRightSide();

        HorizontalLayout columnLayout = new HorizontalLayout();
        columnLayout.setMargin(false);
        columnLayout.setId("task-columns");
        columnLayout.addComponents(leftSideLayout, rightSideLayout);
        columnLayout.setWidth("100%");
        columnLayout.setHeight("100%");
        columnLayout.setExpandRatio(leftSideLayout, 1.0f);

        return new ArrayList<>(Arrays.asList(
                columnLayout
        ));
    }


    private VerticalLayout getLeftSide() {

        VerticalLayout leftSide = new VerticalLayout();
        leftSide.setMargin(false);

        HorizontalLayout projectNameStatus = new HorizontalLayout(projectComboBox, nameTextField, statusNativeSelect);
        projectNameStatus.setMargin(false);
        projectNameStatus.setWidth("100%");
        projectNameStatus.setExpandRatio(nameTextField, 1.0f);

        HorizontalLayout deadlineParent = new HorizontalLayout(deadlineDateField, parentTaskComboBox);
        deadlineParent.setMargin(false);

        HorizontalLayout typePriorityDeadline = new HorizontalLayout(typeNativeSelect, priorityNativeSelect, deadlineDateField);
        typePriorityDeadline.setMargin(false);
        typePriorityDeadline.setWidth("100%");

        leftSide.addComponents(projectNameStatus, typePriorityDeadline, descriptionTextArea);
        leftSide.setExpandRatio(descriptionTextArea, 1.0f);
        leftSide.setHeight("100%");
        leftSide.setWidth("100%");

        return leftSide;
    }


    private VerticalLayout getRightSide() {

        VerticalLayout rightSideLayout = new VerticalLayout();
        rightSideLayout.setMargin(false);

        rightSideLayout.addComponents(assignedUsersListBuilder, subtasksListBuilder);
        rightSideLayout.setExpandRatio(assignedUsersListBuilder, 0.5f);
        rightSideLayout.setExpandRatio(subtasksListBuilder, 0.5f);

        rightSideLayout.setHeight("100%");
        rightSideLayout.setWidth("-1px");

        return rightSideLayout;
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


    protected Binder<Task> initializeDataBinder() {

        Binder<Task> dataBinder = new Binder<>(Task.class);

        dataBinder.forField(nameTextField)
                .withValidator(name -> name.length() >= 3, "Name must be at least 3 characters long.")
                .withValidator(name -> name.length() < 21, "Name is too long! (Max 20)")
                .withValidator(name -> name.equals(name.trim()), "Name containing white spaces!")
                .bind(Task::getName, Task::setName);
        dataBinder.forField(statusNativeSelect)
                .bind(Task::getStatus, Task::setStatus);
        dataBinder.forField(priorityNativeSelect)
                .bind(Task::getPriority, Task::setPriority);
        dataBinder.forField(descriptionTextArea)
                .bind(Task::getDescription, Task::setDescription);
        dataBinder.forField(deadlineDateField)
                .bind(Task::getDeadline, Task::setDeadline);
        dataBinder.forField(typeNativeSelect)
                .bind(Task::getType, Task::setType);
        dataBinder.forField(parentTaskComboBox)
                .bind(Task::getParentTask, Task::setParentTask);
        dataBinder.forField(projectComboBox)
                .bind(Task::getProject, Task::setProject);
        dataBinder.forField(subtasksListBuilder)
                .bind(Task::getSubtasks, Task::setSubtasks);
        dataBinder.forField(assignedUsersListBuilder)
                .bind(Task::getAssignedUsers, Task::setAssignedUsers);

        return dataBinder;
    }


    @Override
    protected void loadData() {

        Collection<User> users = userService.findAll();
        assignedUsersListBuilder.setItems(users);
        Collection<Task> tasks = taskService.findAll();
        subtasksListBuilder.setItems(tasks);
        Collection<Project> projects = projectService.findAll();
        projectComboBox.setItems(projects);
    }


    @Override
    public Task createEmptyEntity() {

        Task empty = new Task();

        return empty;
    }


}
