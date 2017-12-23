package pl.mesayah.assistance.project;

import com.vaadin.navigator.View;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.*;
import pl.mesayah.assistance.issue.Issue;
import pl.mesayah.assistance.milestone.Milestone;
import pl.mesayah.assistance.task.Task;
import pl.mesayah.assistance.team.Team;

import java.util.ArrayList;
import java.util.List;

public class ProjectDetailsView extends VerticalLayout implements View {

    private Project project;

    private HorizontalLayout titlePhaseLayout;

    private Label titleLabel;

    private Label phaseLabel;

    private Label descriptionLabel;

    private HorizontalLayout datesLayout;

    private Label startDateLabel;

    private Label deadlineLabel;

    private VerticalLayout teamsLayout;

    private List<Link> teamsList;

    private VerticalLayout tasksLayout;

    private List<Link> tasksList;

    private VerticalLayout milestonesLayout;

    private List<Link> milestonesList;

    private VerticalLayout issuesLayout;

    private List<Link> issuesList;

    public ProjectDetailsView(Project project) {

        this.project = project;

        setWidth("100%");

        titleLabel = new Label(project.getName());
        titleLabel.setCaption("Name: ");
        titleLabel.setWidth("100%");
        titleLabel.setStyleName("title");
        phaseLabel = new Label(project.getPhase().toString());
        phaseLabel.setCaption("Phase: ");
        titlePhaseLayout = new HorizontalLayout(titleLabel, phaseLabel);
        titlePhaseLayout.setWidth("100%");
        titlePhaseLayout.setExpandRatio(titleLabel, 1.0f);
        titlePhaseLayout.setComponentAlignment(titleLabel, Alignment.MIDDLE_LEFT);
        titlePhaseLayout.setComponentAlignment(phaseLabel, Alignment.MIDDLE_RIGHT);
        addComponent(titlePhaseLayout);

        descriptionLabel = new Label(project.getDescription());
        descriptionLabel.setCaption("Description: ");
        descriptionLabel.setWidth("100%");
        addComponent(descriptionLabel);

        datesLayout = new HorizontalLayout();
        datesLayout.setWidth("100%");
        startDateLabel = new Label(project.getStartTime().toLocalDate().toString());
        startDateLabel.setCaption("Started: ");
        datesLayout.addComponent(startDateLabel);
        deadlineLabel = new Label(project.getDeadline().toLocalDate().toString());
        deadlineLabel.setCaption("Deadline: ");
        datesLayout.addComponent(deadlineLabel);
        addComponent(datesLayout);

        teamsLayout = new VerticalLayout();
        teamsLayout.setCaption("Teams:");
        addComponent(teamsLayout);
        teamsList = new ArrayList<>();
        for (Team t : project.getTeams()) {
            // TODO: get URL for team details view for a link to a corresponding team
            Link teamLink = new Link(t.getName(), new ExternalResource(""));
            teamsList.add(teamLink);
            addComponent(teamLink);
        }

        tasksLayout = new VerticalLayout();
        tasksLayout.setCaption("Tasks:");
        addComponent(tasksLayout);
        tasksList = new ArrayList<>();
        issuesList = new ArrayList<>();
        for (Task t : project.getTasks()) {
            if (t instanceof Issue) {
                // TODO: get URL for issue details view for a link to a corresponding issue
                Link issueLink = new Link(t.getName(), new ExternalResource(""));
                issuesList.add(issueLink);
            } else {
                // TODO: get URL for task details view for a link to a corresponding task
                Link taskLink = new Link(t.getName(), new ExternalResource(""));
                tasksList.add(taskLink);
                addComponent(taskLink);
            }
        }

        milestonesLayout = new VerticalLayout();
        milestonesLayout.setCaption("Milestones:");
        addComponent(milestonesLayout);
        milestonesList = new ArrayList<>();
        for (Milestone m : project.getMilestones()) {
            // TODO: get URL for team details view for a link to a corresponding team
            Link milestoneLink = new Link(m.getName(), new ExternalResource(""));
            milestonesList.add(milestoneLink);
            addComponent(milestoneLink);
        }

        issuesLayout = new VerticalLayout();
        issuesLayout.setCaption("Issues:");
        addComponent(issuesLayout);
        for (Link l : issuesList) {
            addComponent(l);
        }
    }
}
