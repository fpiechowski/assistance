package pl.mesayah.assistance.user;

import com.vaadin.data.ValueProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mesayah.assistance.ui.list.AbstractListView;

import java.util.Collection;
import java.util.List;

@SpringView(name = UserListView.VIEW_NAME)
public class UserListView extends AbstractListView<User> {

    public static final String VIEW_NAME = "users";

    @Autowired
    private UserService userService;


    @Override
    protected User createEmptyEntity() {

        return new User();
    }


    @Override
    protected List<Button> initializeAdditionalButtons() {

        return null;
    }


    @Override
    public Grid<User> initializeListing() {

        Grid<User> grid = new Grid<>(User.class);
        grid.setSizeFull();
        grid.setColumns("id", "username");
        grid.addColumn((ValueProvider<User, Integer>) user -> user.getRoles().size());
        grid.getColumns().get(2).setCaption("Roles");
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

        return new Button("New", VaadinIcons.PLUS);
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
    public Collection<User> fetchDataSet() {

        return userService.findAll();
    }
}
