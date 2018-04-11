package pl.mesayah.assistance.project;

import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mesayah.assistance.milestone.Milestone;
import pl.mesayah.assistance.security.SecurityUtils;
import pl.mesayah.assistance.security.role.Role;
import pl.mesayah.assistance.security.role.RoleService;
import pl.mesayah.assistance.team.Team;
import pl.mesayah.assistance.ui.details.AbstractDetailsView;
import pl.mesayah.assistance.user.User;
import pl.mesayah.assistance.user.UserRepository;

import java.time.LocalDate;
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

    private TwinColSelect<Milestone> milestoneListBuilder;

    private TwinColSelect<Team> teamListBuilder;

    /**
     * A unified formatter for start and deadline dates.
     */
    private DateTimeFormatter dateTimeFormatter;

    @Autowired
    private RoleService roleService;

    private Label managerLabel;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;


    /**
     * Constructs a view in a read mode and initializes controls for it.
     */
    public ProjectDetailsView() {

        dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd yyyyy");
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

        managerComboBox = new ComboBox<>("Project Manager");
        managerComboBox.setEmptySelectionAllowed(false);
        managerComboBox.setItemCaptionGenerator((ItemCaptionGenerator<User>) user -> user.getUsername());
        managerComboBox.setRequiredIndicatorVisible(true);

        HorizontalLayout nameManagerPhaseLayout = new HorizontalLayout(nameTextField, managerComboBox, phaseNativeSelect);
        nameManagerPhaseLayout.setWidth("100%");
        nameManagerPhaseLayout.setExpandRatio(nameTextField, 1.0f);

        descriptionTextArea = new TextArea("Description:");
        descriptionTextArea.setWidth("100%");
        descriptionTextArea.setHeight("100%");
        descriptionTextArea.setSizeFull();

        startDateField = new DateField("Start date:");
        startDateField.setRequiredIndicatorVisible(true);
        startDateField.setSizeFull();

        deadlineDateField = new DateField("Deadline:");
        deadlineDateField.setSizeFull();

        milestoneListBuilder = new TwinColSelect<>("Milestones:");

        teamListBuilder = new TwinColSelect<>("Teams:");

        HorizontalLayout editDatesLayout = new HorizontalLayout(startDateField, deadlineDateField);
        editDatesLayout.setWidth("100%");

        HorizontalLayout columns = new HorizontalLayout();
        columns.setMargin(false);

        VerticalLayout leftColumn = new VerticalLayout(nameManagerPhaseLayout, descriptionTextArea, editDatesLayout);
        leftColumn.setMargin(false);
        leftColumn.setSizeFull();
        leftColumn.setExpandRatio(descriptionTextArea, 1.0f);

        VerticalLayout rightColumn = new VerticalLayout();
        rightColumn.setMargin(false);
        rightColumn.setHeight("100%");
        rightColumn.setWidth("-1px");
        rightColumn.addComponents(milestoneListBuilder, teamListBuilder);

        columns.addComponents(leftColumn, rightColumn);
        columns.setSizeFull();
        columns.setExpandRatio(leftColumn, 1.0f);


        return new ArrayList<>(Arrays.asList(
                columns
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


    protected Binder<Project> initializeDataBinder() {

        Binder<Project> dataBinder = new Binder<>(Project.class);

        dataBinder.forField(nameTextField)
                .withValidator(name -> name.length() >= 3, "Name must be at least 3 characters long.")
                .withValidator(name -> name.length() < 21, "Name is too long! (Max 20)")
                .withValidator(name -> name.equals(name.trim()), "Name containing white spaces!")
                .withValidator(name -> projectRepository.findAllByName(name).isEmpty(),"Project with same name already existing!")
                .bind(Project::getName, Project::setName);
        dataBinder.forField(phaseNativeSelect)
                .bind(Project::getPhase, Project::setPhase);
        dataBinder.forField(descriptionTextArea)
                .bind(Project::getDescription, Project::setDescription);
        dataBinder.forField(startDateField)
                .withValidator(Objects::nonNull, "Start date must not be empty.")
                .withValidator(start -> !start.isBefore(LocalDate.now()),"Cannot start in the past")
                .withValidator(start -> !start.isAfter(deadlineDateField.getValue()),"Cannot start after deadline")
                .bind(Project::getStartDate, Project::setStartDate);
        dataBinder.forField(deadlineDateField)
                .withValidator(deadline -> !deadline.isBefore(LocalDate.now()),"Cannot end in the past")
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
    public Project createEmptyEntity() {

        Project empty = new Project();

        // Set default project manager as currently signed in user (if there is one)
        String currentUserUsername = SecurityUtils.getCurrentUserUsername();
        if (currentUserUsername != null && !(currentUserUsername.equals(""))) {
            User currentUser = userRepository.findByUsername(currentUserUsername);
            empty.setManager(currentUser);
        }

        return empty;
    }


}
