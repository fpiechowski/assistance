package pl.mesayah.assistance.project;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A view containing list of all projects created in the application.
 */
public class ProjectListView extends VerticalLayout implements View {

    /**
     * A name used to navigate between views.
     */
    public static final String VIEW_NAME = "projects";

    /**
     * A layout containing add, edit and delte buttons.
     */
    private HorizontalLayout crudButtonsLayout;

    /**
     * A button to add a new project.
     */
    private Button addProjectButton;

    /**
     * A button to edit a selected project.
     */
    private Button editProjectButton;

    /**
     * A button to delete a selected project.
     */
    private Button deleteProjectButton;

    /**
     * A service used to fetch project data.
     */
    @Autowired
    private ProjectService projectService;

    /**
     * Constructs a view and initializes all its controls.
     */
    public ProjectListView() {

        crudButtonsLayout = new HorizontalLayout();
        initializeCrudButtons();
        crudButtonsLayout.addComponents(addProjectButton, editProjectButton, deleteProjectButton);

        // TODO: implement grid layout for projects presentation and add it to the content of this view

        addComponents(crudButtonsLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        // TODO: implement fetching projects from the database and showing them in grid layout
    }

    /**
     * Initializes add, edit and delete buttons.
     */
    private void initializeCrudButtons() {

        addProjectButton = new Button("Add", VaadinIcons.PLUS);
        addProjectButton.addClickListener((Button.ClickListener) clickEvent -> {
            // TODO: implement creating new project
        });

        editProjectButton = new Button("Edit", VaadinIcons.PENCIL);
        editProjectButton.addClickListener((Button.ClickListener) clickEvent -> {
            // TODO: implement editing existing project
        });

        deleteProjectButton = new Button("Delete", VaadinIcons.TRASH);
        deleteProjectButton.addClickListener((Button.ClickListener) clickEvent -> {
            // TODO: implement deleting a project
        });
    }
}
