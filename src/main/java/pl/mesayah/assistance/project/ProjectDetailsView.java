package pl.mesayah.assistance.project;

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

/**
 * A view of project details. It presents parameters of a given project.
 */
@SpringView(name = ProjectDetailsView.VIEW_NAME)
public class ProjectDetailsView extends VerticalLayout implements View {

    /**
     * A view NAME used to navigate between views.
     */
    public static final String VIEW_NAME = "project";

    /**
     * A reference to main user interface class.
     */
    @Autowired
    private SpringNavigator navigator;

    /**
     * A project service to fetch project data.
     */
    @Autowired
    private ProjectService projectService;

    /**
     * A layout containing title and phase of the project.
     */
    private HorizontalLayout titlePhaseLayout;

    /**
     * A label showing a title of the project.
     */
    private Label titleLabel;

    /**
     * A label showing a phase of the project.
     */
    private Label phaseLabel;

    /**
     * A label showing a description of the project.
     */
    private Label descriptionLabel;

    /**
     * A layout containing a project start date and a deadline.
     */
    private HorizontalLayout datesLayout;

    /**
     * A label showing a project start date.
     */
    private Label startDateLabel;

    /**
     * A label showing a project deadline.
     */
    private Label deadlineLabel;

    /**
     * A layout containing links to teams working under this project.
     */
    private VerticalLayout teamsLayout;

    /**
     * A layout containing links to tasks related with this project.
     */
    private VerticalLayout tasksLayout;

    /**
     * A layout containing links to milestones of this project.
     */
    private VerticalLayout milestonesLayout;

    /**
     * A layout containing links to issues reported for this project.
     */
    private VerticalLayout issuesLayout;

    /**
     * Constructs a view and initializes controls for it.
     */
    public ProjectDetailsView() {

        setWidth("100%");

        initializeTitlePhaseLayout();
        addComponent(titlePhaseLayout);

        descriptionLabel = new Label();
        descriptionLabel.setCaption("Description:");
        descriptionLabel.setWidth("100%");
        addComponent(descriptionLabel);

        initializeDatesLayout();
        addComponent(datesLayout);

        teamsLayout = new VerticalLayout();
        teamsLayout.setCaption("Teams:");
        addComponent(teamsLayout);

        tasksLayout = new VerticalLayout();
        tasksLayout.setCaption("Tasks:");
        addComponent(tasksLayout);

        milestonesLayout = new VerticalLayout();
        milestonesLayout.setCaption("Milestones:");
        addComponent(milestonesLayout);

        issuesLayout = new VerticalLayout();
        issuesLayout.setCaption("Issues:");
        addComponent(issuesLayout);
    }

    /**
     * Initializes a start date and a deadline controls.
     */
    private void initializeDatesLayout() {

        datesLayout = new HorizontalLayout();
        datesLayout.setWidth("100%");
        startDateLabel = new Label();
        startDateLabel.setCaption("Started: ");
        datesLayout.addComponent(startDateLabel);
        deadlineLabel = new Label();
        deadlineLabel.setCaption("Deadline: ");
        datesLayout.addComponent(deadlineLabel);
    }

    /**
     * Initializes a layout with a title and a phase labels of the project.
     */
    private void initializeTitlePhaseLayout() {

        titleLabel = new Label();
        titleLabel.setCaption("Name: ");
        titleLabel.setWidth("100%");
        titleLabel.setStyleName("title");
        phaseLabel = new Label();
        phaseLabel.setCaption("Phase: ");
        titlePhaseLayout = new HorizontalLayout(titleLabel, phaseLabel);
        titlePhaseLayout.setWidth("100%");
        titlePhaseLayout.setExpandRatio(titleLabel, 1.0f);
        titlePhaseLayout.setComponentAlignment(titleLabel, Alignment.MIDDLE_LEFT);
        titlePhaseLayout.setComponentAlignment(phaseLabel, Alignment.MIDDLE_RIGHT);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        // try to get project ID parameter from url
        String idParameter = event.getParameterMap().get("id");
        // if ID has been passed as a parameter
        if (idParameter != null && !idParameter.equals("")) {

            // parse string ID to long
            Long projectId = Long.parseLong(idParameter);
            System.out.println(projectId);
            // fetch project with passed ID from the repository
            Project project = projectService.findById(projectId);

            // if project has been found in repository
            if (project != null) {

                // set controls values to fetched project properties
                titleLabel.setValue(project.getName());
                descriptionLabel.setValue(project.getDescription());
                phaseLabel.setValue(project.getPhase().toString());
                startDateLabel.setValue(project.getStartTime().toLocalDate().toString());
                deadlineLabel.setValue(project.getDeadline().toLocalDate().toString());

                // set fetched project's teams, issues and milestones links
                initializeTeamLinks(project);
                initializeIssuesLinks(project);
                initializeMilestonesLinks(project);
            } else {
                // if project not found in repository
                Notification.show(
                        "Can not find a project with a given ID.",
                        Notification.Type.ERROR_MESSAGE
                );
            }
        } else {
            // if ID hasn't been passed in the url as a parameter
            Notification.show(
                    "No project ID has been provided.",
                    Notification.Type.ERROR_MESSAGE
            );
        }
    }

    /**
     * Initializes links to milestones of a given project.
     *
     * @param project a project to initialize milestone links for
     */
    private void initializeMilestonesLinks(Project project) {

        for (Milestone m : project.getMilestones()) {
            // TODO: get URL for team details view for a link to a corresponding team
            Button milestoneLink = new Button(m.getName());
            milestoneLink.addClickListener(
                    // TODO : set nav state
                    (Button.ClickListener) clickEvent ->
                            navigator.navigateTo(""));
            milestonesLayout.addComponent(milestoneLink);
        }
    }

    /**
     * Initializes links to issues of a given project.
     *
     * @param project a project to initialize issues for
     */
    private void initializeIssuesLinks(Project project) {

        for (Task t : project.getTasks()) {
            if (t instanceof Issue) {
                Button issueLink = new Button(t.getName());
                issueLink.addClickListener(
                        // TODO: set nav state for issues and tasks details
                        (Button.ClickListener) clickEvent -> navigator.navigateTo(""));
                issuesLayout.addComponent(issueLink);
            } else {
                Button taskLink = new Button(t.getName());
                taskLink.addClickListener(
                        (Button.ClickListener) clickEvent -> navigator.navigateTo(""));
                tasksLayout.addComponent(taskLink);
            }
        }
    }

    /**
     * Initializes links to teams of a given project.
     *
     * @param project a project to initialize teams for
     */
    private void initializeTeamLinks(Project project) {

        for (Team t : project.getTeams()) {
            Button teamLink = new Button(t.getName());
            teamLink.addClickListener(
                    // TODO : set navigation state for team details
                    (Button.ClickListener) clickEvent -> navigator.navigateTo(""));
            teamsLayout.addComponent(teamLink);
        }
    }
}
