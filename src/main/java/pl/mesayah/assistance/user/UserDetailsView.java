package pl.mesayah.assistance.user;

import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.mesayah.assistance.security.SecurityUtils;
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
        if(!SecurityUtils.hasRole(Role.SUPER_ADMIN)){
            roleTwinColSelect.setEnabled(false);
        }

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

        Button initializeButton = new
                Button("Delete", VaadinIcons.TRASH);
        if(!SecurityUtils.hasRole(Role.SUPER_ADMIN)) {
            initializeButton.setEnabled(false);
            initializeButton.setDescription("t");
        }
        return initializeButton;
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
                .withValidator(name -> name.length() >= 3, "Name must be at least 3 characters long.")
                .withValidator(name -> name.length() < 21, "Name is too long! (Max 20)")
                .withValidator(name -> name.equals(name.trim()), "Name containing white spaces!")
                .bind(User::getUsername, User::setUsername);
        dataBinder.forField(passwordTextField)
                .withValidator(p -> p.equals(confirmPasswordTextField.getValue()), "Passwords do not mach")
                .bind(c -> null, (p,a) -> p.setPassword(passwordEncoder.encode(a)));
        dataBinder.forField(roleTwinColSelect)
                .bind(c ->
                {
                    Set<Role> finalRoles = new HashSet<>();
                    for (Role r : roles) {
                        for (Role r2 : c.getRoles()) {
                            if (r.getName().equals(r2.getName())) {
                                finalRoles.add(r);
                            }
                        }
                    }
                    return finalRoles;
                }, User::setRoles);
        dataBinder.forField(enabledCheckBox)
                .bind(User::isEnabled, User::setEnabled);
        return dataBinder;
    }

    Collection<Role> roles;
    @Override
    protected void loadData() {

        roles = (Collection<Role>) roleService.findAll();
        roleTwinColSelect.setItems(roles);
    }


    @Override
    protected User createEmptyEntity() {

        User empty = new User();
        empty.setRoles(new HashSet<>());
        return empty;
    }


}
