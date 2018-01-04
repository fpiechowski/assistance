package pl.mesayah.assistance.security;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;

public class LoginForm extends VerticalLayout {

    private VerticalLayout formContainer;

    public LoginForm(LoginCallback callback) {

        setMargin(true);
        setSpacing(true);

        setSizeFull();

        formContainer = new VerticalLayout();
        formContainer.setSizeUndefined();

        TextField username = new TextField("Username:");
        formContainer.addComponent(username);

        PasswordField password = new PasswordField("Password:");
        formContainer.addComponent(password);

        username.setWidth("300px");
        password.setWidth("300px");

        Button login = new Button("Login", evt -> {
            String pword = password.getValue();
            password.setValue("");
            if (!callback.login(username.getValue(), pword)) {
                Notification.show("Login failed");
                username.focus();
            }
        });

        formContainer.addComponent(login);

        formContainer.setComponentAlignment(login, Alignment.MIDDLE_RIGHT);

        login.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        addComponent(formContainer);

        setComponentAlignment(formContainer, Alignment.MIDDLE_CENTER);
    }

    @FunctionalInterface
    public interface LoginCallback {

        boolean login(String username, String password);
    }
}
