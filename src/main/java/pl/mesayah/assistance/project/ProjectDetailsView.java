package pl.mesayah.assistance.project;

import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mesayah.assistance.AbstractDetailsView;
import pl.mesayah.assistance.security.Role;
import pl.mesayah.assistance.security.RoleService;
import pl.mesayah.assistance.security.SecurityUtils;
import pl.mesayah.assistance.user.User;
import pl.mesayah.assistance.user.UserRepository;

import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * A view of project details. It presents parameters of a given project.
 */
@SpringView(name = ProjectDetailsView.VIEW_NAME)
public class ProjectDetailsView extends AbstractDetailsView<Project> {

    /**
     * A view name used to navigate between views.
     */
    public static final String VIEW_NAME = "project";

    /**
     * A label showing a title of the project.
     */
    private Label nameLabel;

    /**
     * A Text Field to edit a title of the project.
     */
    private TextField nameTextField;

    /**
     * A label showing a phase of the project.
     */
    private Label phaseLabel;

    /**
     * A drop down menu to select a phase of the project.
     */
    private NativeSelect<Project.Phase> phaseNativeSelect;

    /**
     * A label showing a description of the project.
     */
    private Label descriptionLabel;

    /**
     * A text area for editing project's description.
     */
    private TextArea descriptionTextArea;

    /**
     * A label showing a project start date.
     */
    private Label startDateLabel;

    /**
     * A date field for selecting project's start date.
     */
    private DateField startDateField;

    /**
     * A label showing a project deadline.
     */
    private Label deadlineLabel;

    /**
     * A date field for selecring project's deadline date.
     */
    private DateField deadlineDateField;

    /**
     * A combo box for selecting project managerComboBox username.
     */
    private ComboBox<User> managerComboBox;

    /**
     * A unified formatter for start and deadline dates.
     */
    private DateTimeFormatter dateTimeFormatter;

    @Autowired
    private RoleService roleService;

    private Label managerLabel;

    @Autowired
    private UserRepository userRepository;

    /**
     * Constructs a view in a read mode and initializes controls for it.
     */
    public ProjectDetailsView() {

        dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd yyyyy");
    }

    @Override
    protected List<Component> initializeReadComponents() {

        nameLabel = new Label();
        nameLabel.setCaption("Name: ");
        nameLabel.setWidth("100%");

        phaseLabel = new Label();
        phaseLabel.setCaption("Phase:");

        HorizontalLayout readNamePhaseLayout = new HorizontalLayout(nameLabel, phaseLabel);
        readNamePhaseLayout.setWidth("100%");


        descriptionLabel = new Label();
        descriptionLabel.setCaption("Description:");
        descriptionLabel.setWidth("100%");


        startDateLabel = new Label();
        startDateLabel.setCaption("Started:");

        deadlineLabel = new Label();
        deadlineLabel.setCaption("Deadline:");

        HorizontalLayout readDatesLayout = new HorizontalLayout(startDateLabel, deadlineLabel);
        readDatesLayout.setWidth("100%");


        managerLabel = new Label();
        managerLabel.setCaption("Project Manager");


        return new ArrayList<>(Arrays.asList(
                readNamePhaseLayout,
                descriptionLabel,
                readDatesLayout,
                managerLabel
        ));
    }

    @Override
    protected List<Component> initializeEditComponents() {

        nameTextField = new TextField("Name:");
        nameTextField.setWidth("100%");
        nameTextField.setRequiredIndicatorVisible(true);

        phaseNativeSelect = new NativeSelect<>("Phase:",
                Arrays.asList(Project.Phase.values()));
        phaseNativeSelect.setEmptySelectionAllowed(false);
        phaseNativeSelect.setSelectedItem(Project.Phase.PLANNING);
        phaseNativeSelect.setWidth("200px");
        phaseNativeSelect.setRequiredIndicatorVisible(true);

        HorizontalLayout editNamePhaseLayout = new HorizontalLayout(nameTextField, phaseNativeSelect);


        descriptionTextArea = new TextArea("Description:");
        descriptionTextArea.setWidth("100%");


        startDateField = new DateField("Start date:");
        startDateField.setRequiredIndicatorVisible(true);

        deadlineDateField = new DateField("Deadline:");

        HorizontalLayout editDatesLayout = new HorizontalLayout(startDateField, deadlineDateField);


        managerComboBox = new ComboBox<>("Project Manager");
        managerComboBox.setEmptySelectionAllowed(false);
        managerComboBox.setItemCaptionGenerator((ItemCaptionGenerator<User>) user -> user.getUsername());
        managerComboBox.setRequiredIndicatorVisible(true);


        return new ArrayList<>(Arrays.asList(
                editNamePhaseLayout,
                descriptionTextArea,
                editDatesLayout,
                managerComboBox
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
    protected Binder<Project> initializeDataBinder() {

        Binder<Project> dataBinder = new Binder<>(Project.class);

        dataBinder.forField(nameTextField)
                .withValidator(name -> name.length() > 0, "Name must not be empty.")
                .bind(Project::getName, Project::setName);
        dataBinder.forField(phaseNativeSelect)
                .bind(Project::getPhase, Project::setPhase);
        dataBinder.forField(descriptionTextArea)
                .bind(Project::getDescription, Project::setDescription);
        dataBinder.forField(startDateField)
                .withValidator(Objects::nonNull, "Start date must not be empty.")
                .bind(Project::getStartDate, Project::setStartDate);
        dataBinder.forField(deadlineDateField)
                .bind(Project::getDeadline, Project::setDeadline);
        dataBinder.forField(managerComboBox)
                .withValidator(Objects::nonNull, "Project must have a manager.")
                .bind(Project::getManager, Project::setManager);

        return dataBinder;
    }

    @Override
    protected void loadData() {

        Collection<User> projectManagers = roleService.findByName(Role.PROJECT_MANAGER).getUsers();
        managerComboBox.setItems(projectManagers);
    }

    @Override
    protected Project createEmptyEntity() {

        Project empty = new Project();

        // Set default project manager as currently signed in user (if there is one)
        String currentUserUsername = SecurityUtils.getCurrentUserUsername();
        if (currentUserUsername != null && !(currentUserUsername.equals(""))) {
            User currentUser = userRepository.findByUsername(currentUserUsername);
            empty.setManager(currentUser);
        }

        return empty;
    }

    @Override
    protected void setReadComponentsValues() {

        nameLabel.setValue(getEntity().getName());

        phaseLabel.setValue(getEntity().getPhase().toString());

        descriptionLabel.setValue(getEntity().getDescription());

        startDateLabel.setValue(getEntity().getStartDate().format(dateTimeFormatter));

        // Deadline can be empty (can be null) so we need to check it before setting a label's value.
        String deadline;
        if (getEntity().getDeadline() == null) {
            deadline = null;
        } else {
            deadline = getEntity().getDeadline().format(dateTimeFormatter);
        }
        deadlineLabel.setValue(deadline);

        // Same as before
        String manager;
        if (getEntity().getManager() == null) {
            manager = null;
        } else {
            manager = getEntity().getManager().getUsername();
        }
        managerLabel.setValue(manager);
    }
}
