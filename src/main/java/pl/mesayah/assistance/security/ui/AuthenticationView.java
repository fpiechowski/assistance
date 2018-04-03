package pl.mesayah.assistance.security.ui;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.AlignmentInfo;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import pl.mesayah.assistance.ui.RelaseNotesView;

public class AuthenticationView extends VerticalLayout {


    public AuthenticationView(LoginCallback callback) {

        RelaseNotesView relaseNotesView = new RelaseNotesView();
        setStyleName("wallpaper");
        setId("auth-screen");
        /**
         * Layouts declarations
         */
        VerticalLayout loginBox = new VerticalLayout();
        loginBox.setMargin(false);
        loginBox.addStyleName(MaterialTheme.CARD_1);
        VerticalLayout logoBox = new VerticalLayout();
        logoBox.setMargin(false);
        HorizontalLayout footer = new HorizontalLayout();
        footer.setMargin(false);


        /**
         * Components declaration
         */
        ThemeResource resource = new ThemeResource("img/assistanceprojekt.png");
        Image logo = new Image();
        TextField username = new TextField("Username:");
        username.setWidth("100%");
        PasswordField password = new PasswordField("Password:");
        password.setWidth("100%");

        Button logIn = new Button("Login", event -> {
            String pword = password.getValue();
            password.setValue("");
            if (!callback.login(username.getValue(), pword)) {
                Notification.show("Login failed");
                username.focus();
            }
        });
        logIn.addStyleName(MaterialTheme.BUTTON_PRIMARY);

        Label welcomeLabel = new Label();
        Label footerMiddleLabel = new Label();
        Label footerLeftLabel = new Label();
        Button footerRightLabel = new Button("Help", event -> {
            removeAllComponents();
            addComponent(new RelaseNotesView());
            setSizeFull();
        });

        /**
         * Components setting
         */

        welcomeLabel.setContentMode(ContentMode.HTML);
        welcomeLabel.setValue("Welcome to Assistance. Please log in.");

        footerMiddleLabel.setValue("© 2017 Assistance · All rights reserved");

        footerLeftLabel.setValue("Build " + relaseNotesView.getBuildVersion() + " " + relaseNotesView.getRelaseDate());

        logo.setSource(resource);
        logo.setWidth("40%");
        /**
         * Components add
         */
        footer.addComponents(footerLeftLabel, footerMiddleLabel, footerRightLabel);
        logoBox.addComponent(logo);
        loginBox.addComponents(welcomeLabel, username, password, logIn);
        addComponents(logoBox, loginBox, footer);
        /**
         * Layouts settings
         */
        setSizeFull();
        footer.setSizeFull();
        loginBox.setMargin(new MarginInfo(true, true, true, true));
        loginBox.setSizeUndefined();
        loginBox.setSpacing(true);
        setExpandRatio(footer, 1);
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
