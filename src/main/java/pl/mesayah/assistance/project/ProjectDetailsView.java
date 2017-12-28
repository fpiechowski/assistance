package pl.mesayah.assistance.project;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mesayah.assistance.issue.Issue;
import pl.mesayah.assistance.milestone.Milestone;
import pl.mesayah.assistance.task.Task;
import pl.mesayah.assistance.team.Team;
import pl.mesayah.assistance.utils.YesNoDialog;

import java.util.ArrayList;
import java.util.List;

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
     * A flag that declares if this view is in edit mode.
     */
    private boolean inEditMode;

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
    private HorizontalLayout crudButtonsLayout;

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
    private HorizontalLayout nameAndPhaseLayout;

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
     * A layout containing links to teams working under this project.
     */
    private ListSelect<Team> teamListSelect;

    /**
     * A twin column selector for selecting teams for the project.
     */
    private TwinColSelect<Team> teamsSelector;

    /**
     * A layout containing links to tasks related with this project.
     */
    private ListSelect<Task> taskListSelect;

    /**
     * A twin column selector for selecting tasks for the project.
     */
    private TwinColSelect<Task> tasksSelector;

    /**
     * A layout containing links to milestones of this project.
     */
    private ListSelect<Milestone> milestoneListSelect;

    /**
     * A twin column selector for selecting milestones for the project.
     */
    private TwinColSelect<Milestone> milestonesSelector;

    /**
     * A layout containing links to issues reported for this project.
     */
    private ListSelect<Issue> issueListSelect;

    /**
     * A twin column selector for selecting issues for this project.
     */
    private TwinColSelect<Issue> issuesSelector;

    /**
     * A project which details we show in this view.
     */
    private Project project;

    /**
     * A button for confirming modifications made to project details.
     */
    private Button confirmUpdateButton;

    /**
     * Constructs a view in a read mode and initializes controls for it.
     */
    public ProjectDetailsView() {

        inEditMode = false;

        setWidth("100%");

        crudButtonsLayout = new HorizontalLayout();

        {
            editButton = new Button("Edit", VaadinIcons.PENCIL);
            editButton.setEnabled(false);
            editButton.addClickListener((Button.ClickListener) clickEvent -> enterEditMode());

            confirmUpdateButton = new Button("Confirm", VaadinIcons.CHECK);
            confirmUpdateButton.setEnabled(false);
            confirmUpdateButton.addClickListener((Button.ClickListener) clickEvent -> updateDetails());

            deleteButton = new Button("Delete", VaadinIcons.TRASH);
            deleteButton.setEnabled(false);
            deleteButton.addClickListener((Button.ClickListener) clickEvent -> deleteProject());

            crudButtonsLayout.addComponents(editButton, deleteButton);
        }


        nameAndPhaseLayout = new HorizontalLayout();
        nameAndPhaseLayout.setWidth("100%");

        {
            nameLabel = new Label();
            nameLabel.setCaption("Name: ");
            nameLabel.setWidth("100%");

            nameTextField = new TextField("Name:");
            nameTextField.setWidth("100%");

            phaseLabel = new Label();
            phaseLabel.setCaption("Phase:");

            phaseNativeSelect = new NativeSelect<>("Phase:");

            nameAndPhaseLayout.addComponents(nameLabel, phaseLabel);
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

            deadlineLabel = new Label();
            deadlineLabel.setCaption("Deadline:");

            deadlineDateField = new DateField("Deadline:");

            datesLayout.addComponents(startDateLabel, deadlineLabel);
        }


        teamListSelect = new ListSelect<>();
        teamListSelect.setCaption("Teams:");

        teamsSelector = new TwinColSelect<>("Teams:");


        taskListSelect = new ListSelect<>();
        taskListSelect.setCaption("Tasks:");

        tasksSelector = new TwinColSelect<>("Tasks:");


        milestoneListSelect = new ListSelect<>();
        milestoneListSelect.setCaption("Milestones:");

        milestonesSelector = new TwinColSelect<>("Milestones:");


        issueListSelect = new ListSelect<>();
        issueListSelect.setCaption("Issues:");

        issuesSelector = new TwinColSelect<>("Issues:");


        addComponents(
                crudButtonsLayout,
                nameAndPhaseLayout,
                descriptionLabel,
                datesLayout,
                teamListSelect,
                milestoneListSelect,
                taskListSelect,
                issueListSelect
        );
    }

    /**
     * Replaces presentation components with edit components to edit details of the project.
     */
    private void enterEditMode() {

        if (!inEditMode) {
            crudButtonsLayout.replaceComponent(editButton, confirmUpdateButton);

            nameAndPhaseLayout.replaceComponent(nameLabel, nameTextField);
            nameAndPhaseLayout.replaceComponent(phaseLabel, phaseNativeSelect);
            nameAndPhaseLayout.setExpandRatio(nameTextField, 1.0f);

            replaceComponent(descriptionLabel, descriptionTextArea);

            datesLayout.replaceComponent(startDateLabel, startDateField);
            datesLayout.replaceComponent(deadlineLabel, deadlineDateField);

            replaceComponent(taskListSelect, tasksSelector);
            replaceComponent(teamListSelect, teamsSelector);
            replaceComponent(milestoneListSelect, milestonesSelector);
            replaceComponent(issueListSelect, issuesSelector);

            inEditMode = true;
        } else {
            throw new IllegalStateException("Entered edit mode while in edit mode!");
        }
    }

    /**
     * Fetches new values for the projects from edit controls, updates project object and saves it to the database.
     */
    private void updateDetails() {

        if (project != null) {

            project.setName(nameTextField.getValue());
            project.setPhase(phaseNativeSelect.getValue());
            project.setDescription(descriptionTextArea.getValue());
            project.setStartDate(startDateField.getValue());
            project.setDeadline(deadlineDateField.getValue());
            project.setTeams(teamListSelect.getValue());
            project.setMilestones(new ArrayList<>(milestoneListSelect.getValue()));
            List<Task> tasksAndIssues = new ArrayList<>(taskListSelect.getValue());
            tasksAndIssues.addAll(issueListSelect.getValue());
            project.setTasks(tasksAndIssues);

            projectService.save(project);

            enterReadMode();
        } else {
            throw new IllegalStateException("No project to update!");
        }
    }

    private void deleteProject() {

        if (project != null) {

            YesNoDialog confirmDialog = new YesNoDialog("Delete a project",
                    "Are you sure you want to delete this project?", (Button.ClickListener) clickEvent -> {

                projectService.delete(project.getId());
                navigator.navigateTo(ProjectListView.VIEW_NAME);
            });
        } else {
            throw new IllegalStateException("Trying to delete undefined project.");
        }
    }

    /**
     * Replaces edit components with presentation components to show details of the project.
     */
    private void enterReadMode() {

        if (inEditMode) {
            crudButtonsLayout.replaceComponent(confirmUpdateButton, editButton);

            nameAndPhaseLayout.replaceComponent(nameTextField, nameLabel);
            nameAndPhaseLayout.replaceComponent(phaseNativeSelect, phaseLabel);
            nameAndPhaseLayout.setExpandRatio(nameLabel, 1.0f);

            replaceComponent(descriptionTextArea, descriptionLabel);

            replaceComponent(startDateField, startDateLabel);
            replaceComponent(deadlineDateField, deadlineLabel);

            replaceComponent(teamsSelector, teamListSelect);
            replaceComponent(tasksSelector, taskListSelect);
            replaceComponent(milestonesSelector, milestoneListSelect);
            replaceComponent(issuesSelector, issueListSelect);

            inEditMode = false;
        } else {
            throw new IllegalStateException("Entered read mode while in read mode!");
        }
    }

    public boolean isInEditMode() {

        return inEditMode;
    }

    public void setInEditMode(boolean inEditMode) {

        this.inEditMode = inEditMode;
    }

    /**
     * Path schema:
     * project/{id}/{mode}
     * eg. project/46/edit, project/23/read
     * Default mode is 'read'.
     *
     * @param event
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        //Read parameters from URL.
        String[] parameters = event.getParameters().split("/");

        if (parameters.length == 1 && parameters[0].equals("")) {

            //No ID or mode type passed in url, show error notification
            Notification.show("No project ID passed!", Notification.Type.ERROR_MESSAGE);
        } else {

            //At least ID passed, fetch a project and check for mode parameter
            Long projectId = Long.parseLong(parameters[0]);
            project = projectService.findById(projectId);

            if (project == null) {

                // Project not found
                Notification.show("No project with a given ID in the database!",
                        Notification.Type.ERROR_MESSAGE);
            } else {

                // Project fetched properly, enable edit and delete buttons
                editButton.setEnabled(true);
                deleteButton.setEnabled(true);
                confirmUpdateButton.setEnabled(true);

                if (parameters.length > 1) {

                    //Mode passed as a url parameter, identify and enter it
                    String mode = parameters[1];
                    if (mode.equals("edit")) {
                        enterEditMode();
                    } else if (mode.equals("read")) {
                        enterReadMode();
                    } else {
                        //Unknown mode passed, show notification
                        Notification.show("Unknown mode type passed in URL! Entered read mode by default.",
                                Notification.Type.WARNING_MESSAGE);
                        enterReadMode();
                    }
                } else {
                    // Only ID passed, enter read mode by default
                    enterReadMode();
                }
            }
        }
    }


}
