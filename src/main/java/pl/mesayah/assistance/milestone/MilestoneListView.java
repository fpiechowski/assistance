package pl.mesayah.assistance.milestone;

import com.vaadin.data.ValueProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mesayah.assistance.security.SecurityUtils;
import pl.mesayah.assistance.security.role.Role;
import pl.mesayah.assistance.ui.list.AbstractListView;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@SpringView(name = MilestoneListView.VIEW_NAME)
public class MilestoneListView extends AbstractListView<Milestone> {

    public static final String VIEW_NAME = "milestones";

    @Autowired
    private MilestoneService MilestoneService;


    @Override
    protected Milestone createEmptyEntity() {

        return new Milestone();
    }


    @Override
    protected List<Button> initializeAdditionalButtons() {

        return null;
    }


    @Override
    public Grid<Milestone> initializeListing() {

        Grid<Milestone> grid = new Grid<>(Milestone.class);
        grid.setSizeFull();
        grid.setColumns("id", "name");
        grid.addColumn((ValueProvider<Milestone, String>) Milestone -> Milestone.getName());
        grid.getColumns().get(2).setCaption("Project");
        grid.addColumn((ValueProvider<Milestone, LocalDate>) Milestone -> Milestone.getDeadline());
        grid.getColumns().get(3).setCaption("Deadline");
        return grid;
    }


    @Override
    protected boolean isGridEditable() {

        return false;
    }


    @Override
    protected Button initializeDetailsButton() {

        return new Button("Details", VaadinIcons.EYE);
    }


    @Override
    protected Button initializeNewButton() {

        Button newButton = new Button("New", VaadinIcons.PLUS);
        if(SecurityUtils.hasRole(Role.CLIENT) || SecurityUtils.hasRole(Role.DEVELOPER) ) {
            newButton.setEnabled(false);
            newButton.setDescription("t");
        }
        return newButton;
    }


    @Override
    protected Button initializeEditButton() {


        Button editButton = new Button("Edit", VaadinIcons.PENCIL);
        if(SecurityUtils.hasRole(Role.CLIENT) || SecurityUtils.hasRole(Role.DEVELOPER)) {
            editButton.setEnabled(false);
            editButton.setDescription("t");
        }
        return editButton;
    }


    @Override
    protected Button initializeDeleteButton() {


        Button deleteButton = new Button("Delete", VaadinIcons.TRASH);
        if(SecurityUtils.hasRole(Role.CLIENT) || SecurityUtils.hasRole(Role.DEVELOPER)) {
            deleteButton.setEnabled(false);
            deleteButton.setDescription("t");
        }
        return deleteButton;

    }


    @Override
    public Collection<Milestone> fetchDataSet() {

        return MilestoneService.findAll();
    }
}
