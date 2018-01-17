package pl.mesayah.assistance.team;

import com.vaadin.data.ValueProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mesayah.assistance.ui.AbstractListView;

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
    protected Button initializeNewButton() {

        return new Button("New", VaadinIcons.PLUS);
    }

    @Override
    protected List<Button> initializeAdditionalButtons() {

        return null;
    }

    @Override
    protected Button initializeDetailsButton() {

        return new Button("Details", VaadinIcons.EYE);
    }

    @Override
    public Collection<Team> fetchDataSet() {

        return teamService.findAll();
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
    protected Button initializeEditButton() {

        return new Button("Edit", VaadinIcons.PENCIL);
    }

    @Override
    protected Button initializeDeleteButton() {

        return new Button("Delete", VaadinIcons.TRASH);
    }

    @Override
    protected boolean isGridEditable() {

        return false;
    }
}
