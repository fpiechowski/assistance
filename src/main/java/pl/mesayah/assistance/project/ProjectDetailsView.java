package pl.mesayah.assistance.project;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mesayah.assistance.issue.Issue;
import pl.mesayah.assistance.milestone.Milestone;
import pl.mesayah.assistance.task.Task;
import pl.mesayah.assistance.team.Team;

public class ProjectDetailsView extends VerticalLayout implements View {

    @Autowired
    private ProjectService projectService;

    private HorizontalLayout titlePhaseLayout;

    private Label titleLabel;

    private Label phaseLabel;

    private Label descriptionLabel;

    private HorizontalLayout datesLayout;

    private Label startDateLabel;

    private Label deadlineLabel;

    private VerticalLayout teamsLayout;


    private VerticalLayout tasksLayout;


    private VerticalLayout milestonesLayout;


    private VerticalLayout issuesLayout;


    public ProjectDetailsView() {

        setWidth("100%");

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
        addComponent(titlePhaseLayout);

        descriptionLabel = new Label();
        descriptionLabel.setCaption("Description: ");
        descriptionLabel.setWidth("100%");
        addComponent(descriptionLabel);

        datesLayout = new HorizontalLayout();
        datesLayout.setWidth("100%");
        startDateLabel = new Label();
        startDateLabel.setCaption("Started: ");
        datesLayout.addComponent(startDateLabel);
        deadlineLabel = new Label();
        deadlineLabel.setCaption("Deadline: ");
        datesLayout.addComponent(deadlineLabel);
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

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        Long projectId = Long.parseLong(event.getParameterMap().get("projectId"));
        Project project = projectService.findById(projectId);

        titleLabel.setValue(project.getName());
        descriptionLabel.setValue(project.getDescription());
        phaseLabel.setValue(project.getPhase().toString());
        startDateLabel.setValue(project.getStartTime().toLocalDate().toString());
        deadlineLabel.setValue(project.getDeadline().toLocalDate().toString());

        for (Team t : project.getTeams()) {
            // TODO: get URL for team details view for a link to a corresponding team
            Link teamLink = new Link(t.getName(), new ExternalResource(""));
            teamsLayout.addComponent(teamLink);
        }

        for (Task t : project.getTasks()) {
            if (t instanceof Issue) {
                // TODO: get URL for issue details view for a link to a corresponding issue
                Link issueLink = new Link(t.getName(), new ExternalResource(""));
                issuesLayout.addComponent(issueLink);
            } else {
                // TODO: get URL for task details view for a link to a corresponding task
                Link taskLink = new Link(t.getName(), new ExternalResource(""));
                tasksLayout.addComponent(taskLink);
            }
        }

        for (Milestone m : project.getMilestones()) {
            // TODO: get URL for team details view for a link to a corresponding team
            Link milestoneLink = new Link(m.getName(), new ExternalResource(""));
            milestonesLayout.addComponent(milestoneLink);
        }
    }
}
