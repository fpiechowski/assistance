package pl.mesayah.assistance.user;

import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.mesayah.assistance.security.role.Role;
import pl.mesayah.assistance.security.role.RoleService;
import pl.mesayah.assistance.ui.details.AbstractDetailsView;

import java.util.*;

@SpringView(name = UserDetailsView.VIEW_NAME)
public class UserDetailsView extends AbstractDetailsView<User> {

    /**
     * A view name used to navigate between views.
     */

    public static final String VIEW_NAME = "user";

    @Autowired
    RoleService roleService;
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * A Text Field to edit a title of the project.
     */
    private TextField usernameTextField;

    /**
     * A Twin Column Selector for selecting roles.
     */
    private TwinColSelect<Role> roleTwinColSelect;

    /**
     * A Text Field to edit a title of the project.
     */
    private TextField passwordTextField;


    @Override
    protected List<Component> initializeEditComponents() {

        VerticalLayout container = new VerticalLayout();
        container.setMargin(false);
        container.setSizeFull();

        usernameTextField = new TextField("Username:");
        usernameTextField.setWidth("100%");
        usernameTextField.setRequiredIndicatorVisible(true);

        passwordTextField = new TextField("Password:");
        passwordTextField.setWidth("100%");
        passwordTextField.setRequiredIndicatorVisible(true);

        roleTwinColSelect = new TwinColSelect<>("Choose roles of the user");
        roleTwinColSelect.setItemCaptionGenerator((ItemCaptionGenerator<Role>) role -> role.getName());

        container.addComponents(usernameTextField, passwordTextField, roleTwinColSelect);
        container.setExpandRatio(roleTwinColSelect, 1.0f);

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
    protected Binder<User> initializeDataBinder() {

        Binder<User> dataBinder = new Binder<>(User.class);

        dataBinder.forField(usernameTextField)
                .withValidator(name -> name.length() > 0, "Name must not be empty.")
                .bind(User::getUsername, User::setUsername);
        dataBinder.forField(passwordTextField)
                .bind(User::getPassword, (p,a) -> p.setPassword(passwordEncoder.encode(a)));
        dataBinder.forField(roleTwinColSelect)
                //.withConverter(Converter.from()
                .bind(c -> (Set) c.getRoles(), User::setRoles);
        //dataBinder.forField(roleTwinColSelect).
        return dataBinder;
    }


    @Override
    protected void loadData() {

        Collection<Role> roles = roleService.findAll();
        roleTwinColSelect.setItems(roles);
    }


    @Override
    protected User createEmptyEntity() {

        User empty = new User();

        return empty;
    }


}
