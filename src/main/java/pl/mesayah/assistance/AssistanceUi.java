package pl.mesayah.assistance;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.mesayah.assistance.security.LoginForm;
import pl.mesayah.assistance.security.SecurityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Main user interface class of the application.
 */
@SpringUI
@SpringViewDisplay
public class AssistanceUi extends UI implements ViewDisplay {

    /**
     * A manager for authentication processes.
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * A navigator which manages views and navigates between them.
     */
    @Autowired
    private SpringNavigator navigator;

    /**
     * A top bar with user information and navigation links.
     */
    private HorizontalLayout topBarLayout;

    /**
     * A container for views presenting different parts of the application.
     */
    private VerticalLayout viewDisplay;

    /**
     * A container for the whole user interface.
     */
    private VerticalLayout rootLayout;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        // Show login form or application main user interface
        getPage().setTitle(AssistanceApplication.NAME);
        if (!SecurityUtils.isLoggedIn()) {
            showLoginForm();
        } else {
            showApplicationUi();
        }
    }

    private void showApplicationUi() {

        initializeTopBarLayout();
        initializeViewDisplay();
        initializeRootLayout();

        setContent(rootLayout);
    }

    private void showLoginForm() {

        setContent(new LoginForm(this::login));
    }

    private boolean login(String username, String password) {

        try {
            Authentication token = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
            // Reinitialize the session to protect against session fixation attacks. This does not work
            // with websocket communication.
            VaadinService.reinitializeSession(VaadinService.getCurrentRequest());
            SecurityContextHolder.getContext().setAuthentication(token);
            // Now when the session is reinitialized, we can enable websocket communication. Or we could have just
            // used WEBSOCKET_XHR and skipped this step completely.
            getPushConfiguration().setTransport(Transport.WEBSOCKET);
            getPushConfiguration().setPushMode(PushMode.AUTOMATIC);
            // Show the main UI
            showApplicationUi();
            return true;
        } catch (AuthenticationException ex) {
            return false;
        }
    }

    private void logout() {

        getPage().reload();
        getSession().close();
    }

    private void handleError(com.vaadin.server.ErrorEvent event) {

        Throwable t = DefaultErrorHandler.findRelevantThrowable(event.getThrowable());
        if (t instanceof AccessDeniedException) {
            Notification.show("You do not have permission to perform this operation",
                    Notification.Type.WARNING_MESSAGE);
        } else {
            DefaultErrorHandler.doDefault(event);
        }
    }

    /**
     * Initializes a container for the whole user interface.
     */
    private void initializeRootLayout() {

        rootLayout = new VerticalLayout();
        rootLayout.setMargin(false);
        rootLayout.addComponents(topBarLayout, viewDisplay);
        rootLayout.setSizeFull();
        rootLayout.setExpandRatio(viewDisplay, 1.0f);
    }

    /**
     * Initializes a layout for showing different application views.
     */
    private void initializeViewDisplay() {

        viewDisplay = new VerticalLayout();
        viewDisplay.setSizeFull();
        viewDisplay.setMargin(false);
    }

    /**
     * Initializes top bar with user information and navigation links.
     */
    private void initializeTopBarLayout() {

        topBarLayout = new HorizontalLayout();
        topBarLayout.setWidth("100%");
        topBarLayout.setHeight("-1px");
        Layout navigationLayout = new NavigationLayout();
        Layout userInfoLayout = new UserInfoLayout();
        topBarLayout.addComponents(userInfoLayout, navigationLayout);
        topBarLayout.setExpandRatio(navigationLayout, 1.0f);
    }

    @Override
    public void showView(View view) {

        viewDisplay.removeAllComponents();
        viewDisplay.addComponent((Component) view);
    }

    /**
     * A layout containing navigation links at the top bar of the user interface.
     */
    private class NavigationLayout extends HorizontalLayout {

        /**
         * A list of links composing the application navigation.
         */
        private List<Button> navigationButtons;

        /**
         * Constructs a layout with navigation links and sets their click listeners.
         */
        public NavigationLayout() {

            this.setWidth("100%");
            this.setHeight("100%");

            navigationButtons = new ArrayList<>();

            Button homeButton = new Button("Home");
            homeButton.addClickListener((Button.ClickListener) clickEvent -> navigator.navigateTo(""));
            navigationButtons.add(homeButton);

            Button tasksButton = new Button("Tasks");
            tasksButton.addClickListener(
                    (Button.ClickListener) clickEvent -> navigator.navigateTo("/task"));
            navigationButtons.add(tasksButton);

            Button projectsButton = new Button("Projects");
            projectsButton.addClickListener(
                    (Button.ClickListener) clickEvent -> navigator.navigateTo("projects"));
            navigationButtons.add(projectsButton);

            Button issuesButton = new Button("Issues");
            issuesButton.addClickListener(
                    (Button.ClickListener) clickEvent -> navigator.navigateTo("/issue"));
            navigationButtons.add(issuesButton);

            this.addComponents(homeButton, tasksButton, projectsButton, issuesButton);

            // Sets the style for all navigation links
            for (Button b : navigationButtons) {
                b.setStyleName("link");
                this.setComponentAlignment(b, Alignment.MIDDLE_CENTER);
            }
        }
    }

    /**
     * A layout containing current user information.
     */
    private class UserInfoLayout extends VerticalLayout {

        /**
         * A button for showing setting view.
         */
        private Button settingLink;

        /**
         * A button for showing current user profile.
         */
        private Button userNameLink;

        public UserInfoLayout() {

            this.setWidth("-1px");
            this.setHeight("-1px");
            this.setMargin(new MarginInfo(false, true));
            this.setId("userInfoLayout");

            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            userNameLink = new Button(username);
            userNameLink.setStyleName("link");
            // TODO: set navigation state to user profile view NAME with user ID parameter
            userNameLink.addClickListener(
                    (Button.ClickListener) clickEvent -> navigator.navigateTo(""));

            settingLink = new Button("Settings", VaadinIcons.COG);
            settingLink.setStyleName("link");
            // TODO: set navigation state to setting view NAME
            settingLink.addClickListener(
                    (Button.ClickListener) clickEvent -> navigator.navigateTo(""));

            this.addComponents(userNameLink, settingLink);
        }
    }
}
