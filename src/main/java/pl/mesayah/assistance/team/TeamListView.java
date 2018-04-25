package pl.mesayah.assistance.team;

import com.vaadin.data.ValueProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mesayah.assistance.security.SecurityUtils;
import pl.mesayah.assistance.security.role.Role;
import pl.mesayah.assistance.ui.list.AbstractListView;

import java.util.Collection;
import java.util.List;

@SpringView(name = TeamListView.VIEW_NAME)
public class TeamListView extends AbstractListView<Team> {

    public static final String VIEW_NAME = "teams";

    @Autowired
    private TeamService teamService;


    @Override
    protected Team createEmptyEntity() {

        return new Team();
    }


    @Override
    protected List<Button> initializeAdditionalButtons() {

        return null;
    }


    @Override
    public Grid<Team> initializeListing() {

        Grid<Team> grid = new Grid<>(Team.class);
        grid.setSizeFull();
        grid.setColumns("id", "name");
        grid.addColumn((ValueProvider<Team, Integer>) team -> team.getMembers().size());
        grid.getColumns().get(2).setCaption("Number of members");
        return grid;
    }


    @Override
    protected boolean isGridEditable() {

        return false;
    }


    @Override
    protected Button initializeDetailsButton() {

        Button initializeButton = new
                Button("Details", VaadinIcons.EYE);
        if(!SecurityUtils.hasRole(Role.SUPER_ADMIN) && !SecurityUtils.hasRole(Role.PROJECT_MANAGER)) {
            initializeButton.setEnabled(false);
            initializeButton.setDescription("t");
        }
        return initializeButton;
    }


    @Override
    protected Button initializeNewButton() {

        Button newButton = new
                Button("New", VaadinIcons.PLUS);
        if(!SecurityUtils.hasRole(Role.SUPER_ADMIN) && !SecurityUtils.hasRole(Role.PROJECT_MANAGER)) {
            newButton.setEnabled(false);
            newButton.setDescription("t");
        }
        return newButton;
    }


    @Override
    protected Button initializeEditButton() {


        Button editButton = new Button("Edit", VaadinIcons.PENCIL);
        if(!SecurityUtils.hasRole(Role.SUPER_ADMIN) && !SecurityUtils.hasRole(Role.PROJECT_MANAGER)) {
            editButton.setEnabled(false);
            editButton.setDescription("t");
        }
        return editButton;
    }


    @Override
    protected Button initializeDeleteButton() {


        Button deleteButton = new Button("Delete", VaadinIcons.TRASH);
        if(!SecurityUtils.hasRole(Role.SUPER_ADMIN) && !SecurityUtils.hasRole(Role.PROJECT_MANAGER)) {
            deleteButton.setEnabled(false);
            deleteButton.setDescription("t");
        }
        return deleteButton;

    }


    @Override
    public Collection<Team> fetchDataSet() {

        return teamService.findAll();
    }
}
