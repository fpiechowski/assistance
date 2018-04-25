package pl.mesayah.assistance.issue;

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
    public Grid<Issue> initializeListing() {

        Grid<Issue> grid = new Grid<>(Issue.class);
        grid.setSizeFull();
        grid.setColumns("id", "name", "status", "reportDate", "reportingUser");
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

        return newButton;
    }


    @Override
    protected Button initializeEditButton() {


        Button editButton = new Button("Edit", VaadinIcons.PENCIL);
        if(SecurityUtils.hasRole(Role.CLIENT)) {
            editButton.setEnabled(false);
            editButton.setDescription("t");
        }
        return editButton;
    }


    @Override
    protected Button initializeDeleteButton() {


        Button deleteButton = new Button("Delete", VaadinIcons.TRASH);
        if(SecurityUtils.hasRole(Role.CLIENT)) {
            deleteButton.setEnabled(false);
            deleteButton.setDescription("t");
        }
        return deleteButton;

    }


    @Override
    public Collection<Issue> fetchDataSet() {

        return issueService.findAll();
    }
}
