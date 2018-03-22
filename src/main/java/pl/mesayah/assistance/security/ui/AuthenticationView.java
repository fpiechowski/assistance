package pl.mesayah.assistance.security.ui;

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
        Button footerRightLabel = new Button("Help", event -> {
            removeAllComponents();
            setStyleName("relaseNotesView");
            addComponent(new RelaseNotesView());
            setSizeFull();
        });

        /**
         * Components setting
         */
        logIn.setStyleName(ValoTheme.BUTTON_PRIMARY);


        username.setStyleName("loginField");

        password.setStyleName("loginField");

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
