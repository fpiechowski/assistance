package pl.mesayah.assistance.issue;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mesayah.assistance.ui.list.AbstractListView;

import java.util.Collection;
import java.util.List;

@SpringView(name = IssueListView.VIEW_NAME)
public class IssueListView extends AbstractListView<Issue> {

    public static final String VIEW_NAME = "issues";

    @Autowired
    private IssueService issueService;

    @Override
    protected Issue createEmptyEntity() {

        return new Issue();
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
    protected Button initializeNewButton() {

        return new Button("Report", VaadinIcons.PLUS);
    }

    @Override
    public Collection<Issue> fetchDataSet() {

        return issueService.findAll();
    }

    @Override
    public Grid<Issue> initializeListing() {

        Grid<Issue> grid = new Grid<>(Issue.class);
        grid.setSizeFull();
        grid.setColumns("id", "name", "status", "reportDate", "reportingUser");
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
