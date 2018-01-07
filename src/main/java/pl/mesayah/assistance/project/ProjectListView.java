package pl.mesayah.assistance.project;

import com.vaadin.event.MouseEvents;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Responsive;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mesayah.assistance.utils.YesNoDialog;

import java.util.List;

/**
 * A view containing list of all projects created in the application.
 */
@SpringView(name = ProjectListView.VIEW_NAME)
public class ProjectListView extends VerticalLayout implements View {

    /**
     * A name used to navigate between views.
     */
    public static final String VIEW_NAME = "projects";

    /**
     * A navigator for switching between views.
     */
    @Autowired
    private SpringNavigator navigator;

    /**
     * A service used to fetch project data.
     */
    @Autowired
    private ProjectService projectService;

    /**
     * A button for adding a new project.
     */
    private Button addButton;

    /**
     * A layout for presenting grid of projects panels.
     */
    private CssLayout projectsTilesLayout;

    /**
     * Constructs a view and initializes all its controls.
     */
    public ProjectListView() {

        addButton = new Button("New Project", VaadinIcons.PLUS);
        addButton.addClickListener((Button.ClickListener) clickEvent -> navigator.navigateTo(ProjectDetailsView.VIEW_NAME + "/" +
                ProjectDetailsView.CREATE_MODE));

        projectsTilesLayout = new CssLayout();

        addComponents(addButton, projectsTilesLayout);

        setComponentAlignment(addButton, Alignment.MIDDLE_RIGHT);
        setComponentAlignment(projectsTilesLayout, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        projectsTilesLayout.setWidth("100%");

        Responsive.makeResponsive(projectsTilesLayout);

        List<Project> projects = projectService.findAll();

        for (Project p : projects) {

            ProjectTile tile = new ProjectTile(p);
            projectsTilesLayout.addComponent(tile);
        }
    }

    /**
     * A panel presenting a tile of a project.
     */
    private class ProjectTile extends Panel {

        private static final int CAPTION_BAR_HEIGHT = 30;

        private Project project;

        private VerticalLayout contentLayout;

        private Label descriptionLabel;

        private HorizontalLayout buttonsLayout;

        private Button deleteButton;

        private Button editButton;

        public ProjectTile(Project project) {

            this.project = project;

            this.setWidth("20%");

            setCaption(project.getName());

            this.addClickListener((MouseEvents.ClickListener) clickEvent -> {

                if (clickEvent.getRelativeY() < CAPTION_BAR_HEIGHT) {

                    navigator.navigateTo(ProjectDetailsView.VIEW_NAME + "/" + project.getId());
                }
            });

            descriptionLabel = new Label(project.getDescription());

            deleteButton = new Button(VaadinIcons.TRASH);
            deleteButton.addClickListener((Button.ClickListener) clickEvent -> {

                YesNoDialog confirmDialog = new YesNoDialog("Delete a project",
                        "Are you sure you want to delete this project?",
                        (Button.ClickListener) yesClickEvent -> {

                            projectService.delete(this.project.getId());
                            navigator.navigateTo(ProjectListView.VIEW_NAME);
                        });
                getUI().addWindow(confirmDialog);
            });

            editButton = new Button(VaadinIcons.PENCIL);
            editButton.addClickListener((Button.ClickListener) clickEvent -> {

                navigator.navigateTo(ProjectDetailsView.VIEW_NAME + "/" +
                        project.getId() + "/" +
                        ProjectDetailsView.EDIT_MODE);
            });

            buttonsLayout = new HorizontalLayout(editButton, deleteButton);

            contentLayout = new VerticalLayout(descriptionLabel, buttonsLayout);
            //contentLayout.setSpacing(false);

            contentLayout.setComponentAlignment(buttonsLayout, Alignment.MIDDLE_RIGHT);

            setContent(contentLayout);
        }
    }
}
