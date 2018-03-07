package pl.mesayah.assistance.milestone;

import com.vaadin.data.ValueProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mesayah.assistance.ui.AbstractListView;

import java.util.Collection;
import java.util.List;

@SpringView(name = MilestoneListView.VIEW_NAME)
public class MilestoneListView extends AbstractListView<Milestone> {

    public static final String VIEW_NAME = "Milestones";

    @Autowired
    private MilestoneService MilestoneService;

    @Override
    protected Milestone createEmptyEntity() {

        return new Milestone();
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
    public Collection<Milestone> fetchDataSet() {

        return MilestoneService.findAll();
    }

    @Override
    public Grid<Milestone> initializeListing() {

        Grid<Milestone> grid = new Grid<>(Milestone.class);
        grid.setSizeFull();
        grid.setColumns("id", "name");
        grid.addColumn((ValueProvider<Milestone, String>) Milestone -> Milestone.getName());
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
