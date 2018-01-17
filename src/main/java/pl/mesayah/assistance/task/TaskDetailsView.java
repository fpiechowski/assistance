package pl.mesayah.assistance.task;

import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mesayah.assistance.AbstractDetailsView;
import pl.mesayah.assistance.messaging.Channel;
import pl.mesayah.assistance.messaging.ChannelService;
import pl.mesayah.assistance.project.Project;
import pl.mesayah.assistance.project.ProjectService;
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
     * A label showing a title of the task.
     */
    private Label nameLabel;

    /**
     * A Text Field to edit a title of the task.
     */
    private TextField nameTextField;

    /**
     * A label showing a status of the task.
     */
    private Label statusLabel;

    /**
     * A drop down menu to select a status of the task.
     */
    private NativeSelect<Task.Status> statusNativeSelect;
    /**
     * A label showing a priority of the task.
     */
    private Label assignedUsersLabel;
    /**
     * A drop down menu to select a priority of the task.
     */
    private TwinColSelect<User> assignedUsersListBuilder;
    /**
     * A label showing a priority of the task.
     */
    private Label priorityLabel;
    /**
     * A drop down menu to select a priority of the task.
     */
    private NativeSelect<Task.Priority> priorityNativeSelect;
    /**
     * A label showing a channel of the task.
     */
    private Label parentTaskLabel;
    /**
     * A combo box for selecting task channel.
     */
    private ComboBox<Task> parentTaskComboBox;
    /**
     * A label showing a subtasks of the task.
     */
    private Label subtasksLabel;
    /**
     * A list builder for selecting task subtasks.
     */
    private TwinColSelect<Task> subtasksListBuilder;
    /**
     * A label showing a channel of the task.
     */
    private Label channelLabel;
    /**
     * A combo box for selecting task channel.
     */
    private ComboBox<Channel> channelComboBox;
    /**
     * A label showing a type of the task.
     */
    private Label typeLabel;
    /**
     * A drop down menu to select a type of the task.
     */
    private NativeSelect<Task.Type> typeNativeSelect;
    /**
     * A label showing a project of the task.
     */
    private Label projectLabel;
    /**
     * A combo box for selecting task project.
     */
    private ComboBox<Project> projectComboBox;
    /**
     * A label showing a description of the task.
     */
    private Label descriptionLabel;

    /**
     * A text area for editing task's description.
     */
    private TextArea descriptionTextArea;

    /**
     * A label showing a task deadline.
     */
    private Label deadlineLabel;

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
    private ChannelService channelService;

    @Autowired
    private TaskService taskService;

    /**
     * Constructs a view in a read mode and initializes controls for it.
     */
    public TaskDetailsView() {

        dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd yyyyy");
    }

    @Override
    protected List<Component> initializeReadComponents() {

        nameLabel = new Label();
        nameLabel.setCaption("Name: ");
        nameLabel.setWidth("100%");

        statusLabel = new Label();
        statusLabel.setCaption("Status:");

        HorizontalLayout readNameStatusLayout = new HorizontalLayout(nameLabel, statusLabel);
        readNameStatusLayout.setWidth("100%");


        descriptionLabel = new Label();
        descriptionLabel.setCaption("Description:");
        descriptionLabel.setWidth("100%");

        priorityLabel = new Label();
        priorityLabel.setCaption("Priority:");
        priorityLabel.setWidth("100%");

        deadlineLabel = new Label();
        deadlineLabel.setCaption("Deadline:");

        assignedUsersLabel = new Label();
        assignedUsersLabel.setCaption("Users: ");

        parentTaskLabel = new Label();
        parentTaskLabel.setCaption("Parent task: ");

        subtasksLabel = new Label();
        subtasksLabel.setCaption("Subtask: ");

        channelLabel = new Label();
        channelLabel.setCaption("Channel: ");

        typeLabel = new Label();
        typeLabel.setCaption("Type: ");

        projectLabel = new Label();
        projectLabel.setCaption("Project: ");

        HorizontalLayout readDatesLayout = new HorizontalLayout(deadlineLabel,assignedUsersLabel);
        readDatesLayout.setWidth("100%");

        return new ArrayList<>(Arrays.asList(
                readNameStatusLayout,
                descriptionLabel,
                readDatesLayout
        ));
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
        statusNativeSelect.setWidth("200px");
        statusNativeSelect.setRequiredIndicatorVisible(true);

        priorityNativeSelect = new NativeSelect<>("Priority:",
                Arrays.asList(Task.Priority.values()));
        priorityNativeSelect.setEmptySelectionAllowed(false);
        priorityNativeSelect.setSelectedItem(Task.Priority.MEDIUM);
        priorityNativeSelect.setWidth("200px");
        priorityNativeSelect.setRequiredIndicatorVisible(true);

        typeNativeSelect = new NativeSelect<>("Type:",
                Arrays.asList(Task.Type.values()));
        typeNativeSelect.setEmptySelectionAllowed(false);
        typeNativeSelect.setSelectedItem(null);
        typeNativeSelect.setWidth("200px");
        typeNativeSelect.setRequiredIndicatorVisible(true);


        descriptionTextArea = new TextArea("Description:");
        descriptionTextArea.setWidth("100%");

        deadlineDateField = new DateField("Deadline:");

        parentTaskComboBox = new ComboBox<>("Parent Task");
        parentTaskComboBox.setEmptySelectionAllowed(false);
        parentTaskComboBox.setItemCaptionGenerator((ItemCaptionGenerator<Task>) task -> task.getId()+' '+task.getName());
        parentTaskComboBox.setRequiredIndicatorVisible(false);

        subtasksListBuilder = new TwinColSelect<>("Subtasks");
        subtasksListBuilder.setRows(6);
        subtasksListBuilder.setLeftColumnCaption("Available subtasks");
        subtasksListBuilder.setRightColumnCaption("Selected subtasks");
        subtasksListBuilder.setItemCaptionGenerator((ItemCaptionGenerator<Task>) task -> task.getId()+' '+task.getName());

        assignedUsersListBuilder = new TwinColSelect<>("Assigned to:");
        assignedUsersListBuilder.setRows(6);
        assignedUsersListBuilder.setLeftColumnCaption("Available users");
        assignedUsersListBuilder.setRightColumnCaption("Selected users");
        assignedUsersListBuilder.setItemCaptionGenerator((ItemCaptionGenerator<User>) user -> user.getUsername());

        projectComboBox = new ComboBox<>("Project");
        projectComboBox.setEmptySelectionAllowed(false);
        projectComboBox.setItemCaptionGenerator((ItemCaptionGenerator<Project>) project -> project.getId()+' '+project.getName());
        projectComboBox.setRequiredIndicatorVisible(false);

        channelComboBox = new ComboBox<>("Channel");
        channelComboBox.setEmptySelectionAllowed(false);
        channelComboBox.setItemCaptionGenerator((ItemCaptionGenerator<Channel>) channel -> channel.getName());
        channelComboBox.setRequiredIndicatorVisible(false);
        HorizontalLayout editNameStatusLayout = new HorizontalLayout(
                nameTextField,
                statusNativeSelect,
                priorityNativeSelect,
                typeNativeSelect,
                parentTaskComboBox,
                projectComboBox,
                channelComboBox
        );

        HorizontalLayout editDatesLayout = new HorizontalLayout(deadlineDateField);

        HorizontalLayout listsLayout = new HorizontalLayout(subtasksListBuilder,assignedUsersListBuilder);

        return new ArrayList<>(Arrays.asList(
                editNameStatusLayout,
                descriptionTextArea,
                editDatesLayout,
                listsLayout
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

    protected Binder<Task> initializeDataBinder() {

        Binder<Task> dataBinder = new Binder<>(Task.class);

        dataBinder.forField(nameTextField)
                .withValidator(name -> name.length() > 0, "Name must not be empty.")
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
        dataBinder.forField(channelComboBox)
                .bind(Task::getChannel, Task::setChannel);
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
        Collection<Channel> channels = channelService.findAll();
        channelComboBox.setItems(channels);
        Collection<Project> projects = projectService.findAll();
        projectComboBox.setItems(projects);
    }

    @Override
    public Task createEmptyEntity() {

        Task empty = new Task();

        return empty;
    }

    @Override
    protected void setReadComponentsValues() {

        nameLabel.setValue(getEntity().getName());

        statusLabel.setValue(getEntity().getStatus().toString());

        priorityLabel.setValue(getEntity().getPriority().toString());

        descriptionLabel.setValue(getEntity().getDescription());

        // Deadline can be empty (can be null) so we need to check it before setting a label's value.
        String deadline;
        if (getEntity().getDeadline() == null) {
            deadline = null;
        } else {
            deadline = getEntity().getDeadline().format(dateTimeFormatter);
        }
        deadlineLabel.setValue(deadline);
    }
}
