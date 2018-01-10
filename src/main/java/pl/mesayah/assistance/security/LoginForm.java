package pl.mesayah.assistance.security;

import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.AlignmentInfo;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class LoginForm extends VerticalLayout {


    public LoginForm(LoginCallback callback) {


/*
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



        addComponent(formContainer);

        setComponentAlignment(formContainer, Alignment.MIDDLE_CENTER);*/

        /**
         * Layouts declarations
         */
        VerticalLayout loginBox = new VerticalLayout();
        VerticalLayout logoBox = new VerticalLayout();
        HorizontalLayout footer = new HorizontalLayout();


        /**
         * Components declaration
         */
        ThemeResource resource = new ThemeResource("img/assistanceprojekt.png");
        Image logo = new Image();
        TextField username = new TextField("Username:");
        PasswordField password = new PasswordField("Password:");

        Button logIn = new Button("Login", event -> {
            String pword = password.getValue();
            password.setValue("");
            if (!callback.login(username.getValue(), pword)) {
                Notification.show("Login failed");
                username.focus();
            }
        });

        Label welcomeLabel = new Label();
        Label footerMiddleLabel = new Label();
        Label footerLeftLabel = new Label();
        CheckBox rememberPass = new CheckBox("Remember password");
        Link footerRightLabel = new Link("Help", new ExternalResource(
                "http://www.google.com"));

        /**
         * Components setting
         */
        logIn.setStyleName(ValoTheme.BUTTON_PRIMARY);


        username.setStyleName("loginField");

        password.setStyleName("loginField");

        welcomeLabel.setContentMode(ContentMode.HTML);
        welcomeLabel.setValue("Welcome to Assistance. Please log in.");

        footerMiddleLabel.setValue("© 2017 Assistance · All rights reserved");

        footerLeftLabel.setValue("Build xxx Mon, Dec 11, 2017, 8:34:35 PM UTC");

        logo.setSource(resource);
        logo.setWidth("40%");
        /**
         * Components add
         */
        footer.addComponents(footerLeftLabel, footerMiddleLabel, footerRightLabel);
        logoBox.addComponent(logo);
        loginBox.addComponents(welcomeLabel, username, password, rememberPass, logIn);
        addComponents(logoBox, loginBox, footer);
        /**
         * Layouts settings
         */
        setSizeFull();
        footer.setSizeFull();
        loginBox.setMargin(new MarginInfo(true, true, false, true));
        loginBox.setSizeUndefined();
        loginBox.setSpacing(true);
        setStyleName("loginGeneral");
        setExpandRatio(footer, 1);
        loginBox.setStyleName("loginBox");
        /**
         * Set component alignments
         */
        setComponentAlignment(footer, new Alignment(AlignmentInfo.Bits.ALIGNMENT_BOTTOM | AlignmentInfo.Bits.ALIGNMENT_HORIZONTAL_CENTER));
        footer.setComponentAlignment(footerLeftLabel,
                new Alignment(AlignmentInfo.Bits.ALIGNMENT_LEFT |
                        AlignmentInfo.Bits.ALIGNMENT_BOTTOM));
        footer.setComponentAlignment(footerMiddleLabel,
                new Alignment(AlignmentInfo.Bits.ALIGNMENT_HORIZONTAL_CENTER |
                        AlignmentInfo.Bits.ALIGNMENT_BOTTOM));
        footer.setComponentAlignment(footerRightLabel,
                new Alignment(AlignmentInfo.Bits.ALIGNMENT_RIGHT |
                        AlignmentInfo.Bits.ALIGNMENT_BOTTOM));
        footer.setComponentAlignment(footerLeftLabel,
                new Alignment(AlignmentInfo.Bits.ALIGNMENT_BOTTOM |
                        AlignmentInfo.Bits.ALIGNMENT_LEFT));
        setComponentAlignment(loginBox, new Alignment(AlignmentInfo.Bits.ALIGNMENT_TOP |
                AlignmentInfo.Bits.ALIGNMENT_HORIZONTAL_CENTER));
        logoBox.setComponentAlignment(logo, new Alignment(AlignmentInfo.Bits.ALIGNMENT_TOP |
                AlignmentInfo.Bits.ALIGNMENT_HORIZONTAL_CENTER));
        loginBox.setComponentAlignment(username, new Alignment(AlignmentInfo.Bits.ALIGNMENT_VERTICAL_CENTER |
                AlignmentInfo.Bits.ALIGNMENT_HORIZONTAL_CENTER));
        loginBox.setComponentAlignment(password, new Alignment(AlignmentInfo.Bits.ALIGNMENT_VERTICAL_CENTER |
                AlignmentInfo.Bits.ALIGNMENT_HORIZONTAL_CENTER));
        loginBox.setComponentAlignment(logIn,
                new Alignment(AlignmentInfo.Bits.ALIGNMENT_VERTICAL_CENTER |
                        AlignmentInfo.Bits.ALIGNMENT_RIGHT));


        logIn.setClickShortcut(ShortcutAction.KeyCode.ENTER);
    }

    @FunctionalInterface
    public interface LoginCallback {

        boolean login(String username, String password);
    }
}
