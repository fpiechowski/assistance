package pl.mesayah.assistance.team;

import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mesayah.assistance.ui.details.AbstractDetailsView;
import pl.mesayah.assistance.user.User;
import pl.mesayah.assistance.user.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SpringView(name = TeamDetailsView.VIEW_NAME)
public class TeamDetailsView extends AbstractDetailsView<Team> {

    /**
     * A view name used to navigate between views.
     */

    public static final String VIEW_NAME = "team";

    @Autowired
    UserService userService;

    /**
     * A Text Field to edit a title of the project.
     */
    private TextField nameTextField;

    /**
     * A Twin Column Selector for selecting team members.
     */
    private TwinColSelect<User> userTwinColSelect;




    @Override
    protected List<Component> initializeEditComponents() {

        VerticalLayout container = new VerticalLayout();
        container.setMargin(false);
        container.setSizeUndefined();

        nameTextField = new TextField("Team name:");
        nameTextField.setWidth("100%");
        nameTextField.setRequiredIndicatorVisible(true);

        userTwinColSelect = new TwinColSelect<>("Choose members of the team");
        userTwinColSelect.setSizeFull();

        container.addComponents(nameTextField, userTwinColSelect);
        container.setExpandRatio(userTwinColSelect, 1.0f);

        return new ArrayList<>(Arrays.asList(
                container
        ));
    }


    @Override
    protected Button initializeDeleteButton() {

        return new Button("Delete", VaadinIcons.TRASH);
    }


    @Override
    protected Button initializeConfirmButton() {

        return new Button("Confirm", VaadinIcons.CHECK);
    }


    @Override
    protected Button initializeEditButton() {

        return new Button("Edit", VaadinIcons.PENCIL);
    }


    @Override
    protected Binder<Team> initializeDataBinder() {

        Binder<Team> dataBinder = new Binder<>(Team.class);

        dataBinder.forField(nameTextField)
                .withValidator(name -> name.length() > 0, "Name must not be empty.")
                .bind(Team::getName, Team::setName);
        dataBinder.forField(userTwinColSelect)
                .bind(Team::getMembers, Team::setMembers);

        return dataBinder;
    }


    @Override
    protected void loadData() {

        Collection<User> members = userService.findAll();
        userTwinColSelect.setItems(members);
    }


    @Override
    protected Team createEmptyEntity() {

        Team empty = new Team();

        return empty;
    }


}
