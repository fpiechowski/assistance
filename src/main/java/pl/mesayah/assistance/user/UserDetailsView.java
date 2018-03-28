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
     * A Text Field to edit a password of the user.
     */
    private TextField passwordTextField;

    /**
     * A Text Field to edit a password of the user.
     */
    private TextField confirmPasswordTextField;

    /**
     * A CheckBox to set if the user is enabled.
     */
    private CheckBox enabledCheckBox;


    @Override
    protected List<Component> initializeEditComponents() {

        VerticalLayout container = new VerticalLayout();
        container.setMargin(false);
        container.setSizeFull();

        usernameTextField = new TextField("Username:");
        usernameTextField.setWidth("100%");
        usernameTextField.setRequiredIndicatorVisible(true);

        passwordTextField = new PasswordField("Password:");
        passwordTextField.setWidth("100%");
        passwordTextField.setRequiredIndicatorVisible(true);

        confirmPasswordTextField = new PasswordField("Confirm password:");
        confirmPasswordTextField.setWidth("100%");
        confirmPasswordTextField.setRequiredIndicatorVisible(true);

        roleTwinColSelect = new TwinColSelect<>("Choose roles of the user");
        roleTwinColSelect.setItemCaptionGenerator((ItemCaptionGenerator<Role>) role -> role.getName());

        enabledCheckBox = new CheckBox("Enabled");
        enabledCheckBox.setWidth("100%");

        container.addComponents(usernameTextField, passwordTextField,
                confirmPasswordTextField, enabledCheckBox, roleTwinColSelect);
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
                .withValidator(p -> p.equals(confirmPasswordTextField.getValue()), "Passwords do not mach")
                .bind(c -> null, (p,a) -> p.setPassword(passwordEncoder.encode(a)));
        dataBinder.forField(roleTwinColSelect)
                .bind(u->{
                    roleTwinColSelect.setItems(u.getRoles());
                    return u.getRoles();
                }, User::setRoles);
        dataBinder.forField(enabledCheckBox)
                .bind(User::isEnabled, User::setEnabled);
        return dataBinder;
    }


    @Override
    protected void loadData() {

        Set<Role> roles = roleService.findAll();
        roleTwinColSelect.setItems(roles);
    }


    @Override
    protected User createEmptyEntity() {

        User empty = new User();

        return empty;
    }


}
