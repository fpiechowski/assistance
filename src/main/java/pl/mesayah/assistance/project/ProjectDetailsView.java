package pl.mesayah.assistance.project;

import com.vaadin.data.Binder;
import com.vaadin.data.BinderValidationStatus;
import com.vaadin.data.ValidationException;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.mesayah.assistance.security.Role;
import pl.mesayah.assistance.security.RoleService;
import pl.mesayah.assistance.user.User;
import pl.mesayah.assistance.user.UserService;
import pl.mesayah.assistance.utils.YesNoDialog;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/**
 * A view of project details. It presents parameters of a given project.
 */
@SpringView(name = ProjectDetailsView.VIEW_NAME)
public class ProjectDetailsView extends VerticalLayout implements View {

    /**
     * A view name used to navigate between views.
     */
    public static final String VIEW_NAME = "project";

    /**
     * An url path fragment for entering this view in edit mode to add a new project.
     */
    public static final String CREATE_MODE = "new";

    /**
     * An url path fragment for entering this view in edit mode to modify an existing project.
     */
    public static final String EDIT_MODE = "edit";

    /**
     * An url path fragment for entering this view in read mode to present details of a project.
     */
    public static final String READ_MODE = "read";

    /**
     * A reference to a user interface navigator.
     */
    @Autowired
    private SpringNavigator navigator;

    /**
     * A project service to fetch project data.
     */
    @Autowired
    private ProjectService projectService;

    /**
     * A layout with edit and delete buttons.
     */
    private HorizontalLayout buttonsLayout;

    /**
     * A button for editing details of the project.
     */
    private Button editButton;

    /**
     * A button for deleting the project.
     */
    private Button deleteButton;

    /**
     * A layout containing title and phase of the project.
     */
    private HorizontalLayout namePhaseLayout;

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
     * A layout containing a project start date and a deadline.
     */
    private HorizontalLayout datesLayout;

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
     * A project which details we show in this view.
     */
    private Project project;

    /**
     * A button for confirming modifications made to project details.
     */
    private Button confirmButton;

    /**
     * A unified formatter for start and deadline dates.
     */
    private DateTimeFormatter dateTimeFormatter;

    /**
     * A data binder for binding values of project properties to Vaadin compontent in the UI.
     */
    private Binder<Project> dataBinder;

    /**
     * A user service bean for fetching users from the database.
     */
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private Label managerLabel;

    /**
     * Constructs a view in a read mode and initializes controls for it.
     */
    public ProjectDetailsView() {

        dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd yyyyy");

        buttonsLayout = new HorizontalLayout();

        {
            editButton = new Button("Edit", VaadinIcons.PENCIL);
            editButton.addClickListener((Button.ClickListener) clickEvent ->
                    navigator.navigateTo(ProjectDetailsView.VIEW_NAME + "/" + project.getId() + "/" +
                            ProjectDetailsView.EDIT_MODE));

            confirmButton = new Button("Confirm", VaadinIcons.CHECK);
            confirmButton.addClickListener((Button.ClickListener) clickEvent -> updateDetails());

            deleteButton = new Button("Delete", VaadinIcons.TRASH);
            deleteButton.addClickListener((Button.ClickListener) clickEvent -> showDeleteWindow());
        }


        namePhaseLayout = new HorizontalLayout();
        namePhaseLayout.setWidth("100%");

        {
            nameLabel = new Label();
            nameLabel.setCaption("Name: ");
            nameLabel.setWidth("100%");

            nameTextField = new TextField("Name:");
            nameTextField.setWidth("100%");
            nameTextField.setRequiredIndicatorVisible(true);

            phaseLabel = new Label();
            phaseLabel.setCaption("Phase:");

            phaseNativeSelect = new NativeSelect<>("Phase:",
                    Arrays.asList(Project.Phase.values()));
            phaseNativeSelect.setEmptySelectionAllowed(false);
            phaseNativeSelect.setSelectedItem(Project.Phase.PLANNING);
            phaseNativeSelect.setWidth("200px");
            phaseNativeSelect.setRequiredIndicatorVisible(true);
        }


        descriptionLabel = new Label();
        descriptionLabel.setCaption("Description:");
        descriptionLabel.setWidth("100%");

        descriptionTextArea = new TextArea("Description:");
        descriptionTextArea.setWidth("100%");


        datesLayout = new HorizontalLayout();
        datesLayout.setWidth("100%");

        {
            startDateLabel = new Label();
            startDateLabel.setCaption("Started:");

            startDateField = new DateField("Start date:");
            startDateField.setRequiredIndicatorVisible(true);

            deadlineLabel = new Label();
            deadlineLabel.setCaption("Deadline:");

            deadlineDateField = new DateField("Deadline:");
        }


        managerLabel = new Label();
        managerLabel.setCaption("Project Manager");


        managerComboBox = new ComboBox<>("Project Manager");
        managerComboBox.setEmptySelectionAllowed(false);
        managerComboBox.setItemCaptionGenerator((ItemCaptionGenerator<User>) user -> user.getUsername());
        managerComboBox.setRequiredIndicatorVisible(true);


        dataBinder = new Binder<>(Project.class);
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
        System.out.println(nameTextField.getValue());
    }

    /**
     * Fetches new values for the projects from edit controls, updates project object and saves it to the database.
     */
    private void updateDetails() {

        if (project != null) {

            try {
                dataBinder.writeBean(project);
            } catch (ValidationException e) {
                e.printStackTrace();
                BinderValidationStatus<Project> status = dataBinder.validate();
                return;
            }

            Project saved = projectService.save(project);

            navigator.navigateTo(ProjectDetailsView.VIEW_NAME + "/" + saved.getId());
        } else {
            throw new IllegalStateException("No project to update!");
        }
    }

    private void showDeleteWindow() {

        if (project != null) {

            YesNoDialog confirmDialog = new YesNoDialog("Delete a project",
                    "Are you sure you want to delete this project?", (Button.ClickListener) clickEvent -> {

                projectService.delete(project.getId());
                navigator.navigateTo(ProjectListView.VIEW_NAME);
            });
            getUI().addWindow(confirmDialog);
        } else {
            throw new IllegalStateException("Trying to delete undefined project.");
        }
    }

    public ComboBox<User> getManagerComboBox() {

        return managerComboBox;
    }

    public void setManagerComboBox(ComboBox<User> managerComboBox) {

        this.managerComboBox = managerComboBox;
    }

    /**
     * Method called on view entered.
     * Path schema:
     * #!project/new			CREATE
     * #!project/{id}			READ
     * #!project/{id}/edit		UPDATE
     * <p>
     * #!project/{id}/{operation}	custom operation for specific project
     *
     * @param event an information holder for view change event
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        Collection<User> projectManagers = roleService.findByName(Role.PROJECT_MANAGER).getUsers();
        managerComboBox.setItems(projectManagers);

        //Read parameters from URL.
        String[] parameters = event.getParameters().split("/");

        if (parameters[0].equals(ProjectDetailsView.CREATE_MODE)) {

            // CREATE
            project = new Project();
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                String currentUserName = authentication.getName();
                project.setManager(userService.findByUsername(currentUserName));
            }
            bindComponentsToProject();
            enterCreateMode();
        } else if (StringUtils.isNumeric(parameters[0])) {

            System.out.println("Found project ID parameter");

            // 1st parameter is numeric
            Long projectId = Long.parseLong(parameters[0]);
            project = projectService.findById(projectId);

            if (project != null) {
                bindComponentsToProject();

                if (parameters.length == 2) {

                    System.out.println("Found view mode parameter");

                    switch (parameters[1]) {
                        case ProjectDetailsView.EDIT_MODE: {

                            System.out.println("Recognized view mode parameter as EDIT MODE");
                            // UPDATE
                            enterEditMode();
                            break;
                        }
                        case ProjectDetailsView.READ_MODE: {

                            System.out.println("Recognized view mode parameter as READ MODE");
                            // READ
                            enterReadMode();
                            break;
                        }
                    }
                } else {
                    // READ
                    enterReadMode();
                }
            }
        }
    }

    private void enterCreateMode() {

        switchToEditComponents();
        deleteButton.setVisible(false);
    }

    private void bindComponentsToProject() {

        dataBinder.readBean(project);
    }

    private void enterEditMode() {

        System.out.println("Entered EDIT MODE");

        switchToEditComponents();
        deleteButton.setVisible(true);
    }

    private void enterReadMode() {

        switchToReadComponents();

        setReadLabels();

        deleteButton.setVisible(true);
    }

    /**
     * Replaces edit components with presentation components to show details of the project.
     */
    private void switchToReadComponents() {

        removeAllComponents();

        buttonsLayout.removeAllComponents();
        buttonsLayout.addComponents(
                editButton,
                deleteButton
        );

        namePhaseLayout.removeAllComponents();
        namePhaseLayout.addComponents(
                nameLabel,
                phaseLabel
        );
        namePhaseLayout.setExpandRatio(nameLabel, 1.0f);

        datesLayout.removeAllComponents();
        datesLayout.addComponents(
                startDateLabel,
                deadlineLabel
        );

        addComponents(
                buttonsLayout,
                namePhaseLayout,
                descriptionLabel,
                datesLayout,
                managerLabel
        );

        setComponentAlignment(buttonsLayout, Alignment.MIDDLE_RIGHT);
    }

    private void setReadLabels() {

        nameLabel.setValue(project.getName());
        phaseLabel.setValue(project.getPhase().toString());
        descriptionLabel.setValue(project.getDescription());
        startDateLabel.setValue(project.getStartDate().format(dateTimeFormatter));
        String deadline;
        if (project.getDeadline() == null) {
            deadline = null;
        } else {
            deadline = project.getDeadline().format(dateTimeFormatter);
        }
        deadlineLabel.setValue(deadline);
        String manager;
        if (project.getManager() == null) {
            manager = null;
        } else {
            manager = project.getManager().getUsername();
        }
        managerLabel.setValue(manager);
    }

    /**
     * Replaces presentation components with edit components to edit details of the project.
     */
    private void switchToEditComponents() {

        removeAllComponents();

        buttonsLayout.removeAllComponents();
        buttonsLayout.addComponents(
                confirmButton,
                deleteButton
        );

        namePhaseLayout.removeAllComponents();
        namePhaseLayout.addComponents(
                nameTextField,
                phaseNativeSelect
        );
        namePhaseLayout.setExpandRatio(nameTextField, 1.0f);

        datesLayout.removeAllComponents();
        datesLayout.addComponents(
                startDateField,
                deadlineDateField
        );

        addComponents(
                buttonsLayout,
                namePhaseLayout,
                descriptionTextArea,
                datesLayout,
                managerComboBox
        );

        setComponentAlignment(buttonsLayout, Alignment.MIDDLE_RIGHT);
    }
}
