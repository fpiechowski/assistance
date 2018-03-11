package pl.mesayah.assistance.ui;

import com.vaadin.annotations.Theme;
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
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.mesayah.assistance.AssistanceApplication;
import pl.mesayah.assistance.Entity;
import pl.mesayah.assistance.Repositories;
import pl.mesayah.assistance.issue.Issue;
import pl.mesayah.assistance.milestone.Milestone;
import pl.mesayah.assistance.project.Project;
import pl.mesayah.assistance.security.SecurityUtils;
import pl.mesayah.assistance.security.ui.AuthenticationView;
import pl.mesayah.assistance.task.Task;
import pl.mesayah.assistance.team.Team;
import pl.mesayah.assistance.ui.list.ListViews;

import java.util.*;

/**
 * Main user interface class of the application.
 */
@Theme("mytheme")
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

        navigator.setErrorView(new ErrorView());

        initializeViewDisplay();

        // Show login form or application main user interface
        getPage().setTitle(AssistanceApplication.NAME);
        if (!SecurityUtils.isLoggedIn()) {
            showLoginForm();
        } else {

            showApplicationUi();
        }
    }


    /**
     * Initializes a layout for showing different application views.
     */
    private void initializeViewDisplay() {

        viewDisplay = new VerticalLayout();
        viewDisplay.setId("view-display");
        viewDisplay.setSizeFull();
        viewDisplay.setMargin(false);
    }


    /**
     * Sets content of this UI to login form.
     */
    private void showLoginForm() {

        setContent(new AuthenticationView(this::login));
    }


    /**
     * Sets content of this UI to a main application user interface.
     */
    private void showApplicationUi() {

        initializeTopBarLayout();
        initializeRootLayout();

        setContent(rootLayout);
    }


    /**
     * Initializes top bar with user information and navigation links.
     */
    private void initializeTopBarLayout() {

        topBarLayout = new HorizontalLayout();
        topBarLayout.setId("top-bar");
        topBarLayout.setWidth("100%");
        topBarLayout.setHeight("-1px");
        Layout navigationLayout = new NavigationLayout();
        Layout userInfoLayout = new UserInfoLayout();
        topBarLayout.addComponents(userInfoLayout, navigationLayout);
        topBarLayout.setExpandRatio(navigationLayout, 1.0f);
        topBarLayout.setStyleName("topbarLayout");
    }


    /**
     * Initializes a container for the whole user interface.
     */
    private void initializeRootLayout() {

        rootLayout = new VerticalLayout();
        rootLayout.setId("root");
        rootLayout.setMargin(false);
        rootLayout.addComponents(topBarLayout, viewDisplay);
        rootLayout.setComponentAlignment(viewDisplay, Alignment.TOP_CENTER);
        rootLayout.setSizeFull();
        rootLayout.setExpandRatio(viewDisplay, 1.0f);
    }


    public <T extends Entity> void showDeleteWindow(T itemToDelete) {

        Set<T> set = new HashSet<>();
        set.add(itemToDelete);
        showDeleteWindow(set);
    }


    public <T extends Entity> void showDeleteWindow(Collection<T> itemsToDelete) {

        if (itemsToDelete.size() > 0) {
            T first = itemsToDelete.iterator().next();
            CrudRepository repository = Repositories.getRepositoryFor(first.getClass());

            String caption, message;
            if (itemsToDelete.size() > 1) {
                caption = "Delete " + itemsToDelete.size() + " " + first.getEntityName() + "s";
                message = "Are you sure you want to delete " + itemsToDelete.size() + " " + first.getEntityName() + "s?";
            } else {
                caption = "Delete a " + first.getEntityName();
                message = "Are you sure you want to delete this " + first.getEntityName() + "?";
            }

            YesNoDialog confirmDialog = new YesNoDialog(caption,
                    message, clickEvent -> {

                for (T item : itemsToDelete) {
                    repository.delete(item);
                }

                String listViewState = ListViews.getListViewNameFor(first.getClass());
                if (listViewState != null) {
                    navigator.navigateTo(listViewState);
                } else {
                    navigator.navigateTo("");
                }
            });
            getUI().addWindow(confirmDialog);
        }
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


    private void handleError(com.vaadin.server.ErrorEvent event) {

        Throwable t = DefaultErrorHandler.findRelevantThrowable(event.getThrowable());
        if (t instanceof AccessDeniedException) {
            Notification.show("You do not have permission to perform this operation",
                    Notification.Type.WARNING_MESSAGE);
        } else {
            DefaultErrorHandler.doDefault(event);
        }
    }


    @Override
    public void showView(View view) {

        viewDisplay.removeAllComponents();
        viewDisplay.addComponent((Component) view);
    }


    /**
     * A layout containing current user information.
     */
    class UserInfoLayout extends VerticalLayout {

        /**
         * A button for showing setting view.
         */
        private Button settingLink;

        /**
         * A button for showing current user profile.
         */
        private Button userNameLink;
        private Button logoutButton;


        public UserInfoLayout() {

            this.setWidth("-1px");
            this.setHeight("-1px");
            this.setMargin(new MarginInfo(false, true));
            this.setId("userInfoLayout");
            HorizontalLayout setlog = new HorizontalLayout();
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            userNameLink = new Button(username);
            userNameLink.setStyleName("link");
            // TODO: set navigation state to user profile view NAME with user ID parameter
            userNameLink.addClickListener(
                    (Button.ClickListener) clickEvent -> navigator.navigateTo(""));

            logoutButton = new Button("Logout");
            logoutButton.setStyleName("link");
            logoutButton.addClickListener((Button.ClickListener) clickevent -> logout());

            settingLink = new Button("Settings", VaadinIcons.COG);
            settingLink.setStyleName("link");
            // TODO: set navigation state to setting view NAME
            settingLink.addClickListener(
                    (Button.ClickListener) clickEvent -> navigator.navigateTo(""));
            setlog.addComponents(settingLink, logoutButton);
            this.addComponents(userNameLink, setlog);
        }


        private void logout() {

            getPage().reload();
            getSession().close();
        }
    }

    /**
     * A layout containing navigation links at the top bar of the user interface.
     */
    class NavigationLayout extends HorizontalLayout {

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
                    (Button.ClickListener) clickEvent -> navigator.navigateTo(ListViews.getListViewNameFor(Task.class)));
            navigationButtons.add(tasksButton);

            Button projectsButton = new Button("Projects");
            projectsButton.addClickListener(
                    (Button.ClickListener) clickEvent -> navigator.navigateTo(ListViews.getListViewNameFor(Project.class)));
            navigationButtons.add(projectsButton);

            Button teamsButton = new Button("Teams");
            teamsButton.addClickListener(
                    (Button.ClickListener) clickEvent -> navigator.navigateTo(ListViews.getListViewNameFor(Team.class)));
            navigationButtons.add(teamsButton);

            Button issuesButton = new Button("Issues");
            issuesButton.addClickListener(
                    (Button.ClickListener) clickEvent -> navigator.navigateTo(ListViews.getListViewNameFor(Issue.class)));
            navigationButtons.add(issuesButton);

            Button milestonesButton = new Button("Milestones");
            milestonesButton.addClickListener(
                    (Button.ClickListener) clickEvent -> navigator.navigateTo(ListViews.getListViewNameFor(Milestone.class)));
            navigationButtons.add(milestonesButton);


            // Sets the style for all navigation links
            for (Button b : navigationButtons) {
                b.setStyleName("link");
                addComponent(b);
                this.setComponentAlignment(b, Alignment.MIDDLE_CENTER);
            }
        }
    }
}

