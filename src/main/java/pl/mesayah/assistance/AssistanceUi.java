package pl.mesayah.assistance;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.mesayah.assistance.project.ProjectDetailsView;
import pl.mesayah.assistance.project.ProjectListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Main user interface class of the application.
 */
@SpringUI
public class AssistanceUi extends UI {

    /**
     * A navigator which manages views and navigates between them.
     */
    private Navigator navigator;

    /**
     * A top bar with user information and navigation links.
     */
    private HorizontalLayout topBarLayout;

    /**
     * A container for views presenting different parts of the application.
     */
    private VerticalLayout viewContainerLayout;

    /**
     * A container for the whole user interface.
     */
    private VerticalLayout contentLayout;

    @Override
    public Navigator getNavigator() {

        return navigator;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        initializeTopBarLayout();
        initializeViewContainerLayout();
        initializeContentLayout();
        initializeNavigator();

        setContent(contentLayout);
    }

    /**
     * Initializes the navigator and sets all view that can be presented in the view container layout.
     */
    private void initializeNavigator() {

        navigator = new Navigator(this, viewContainerLayout);

        // Add new views here so they can be navigated to.
        navigator.addView(ProjectListView.VIEW_NAME, new ProjectListView());
        navigator.addView(ProjectDetailsView.VIEW_NAME, new ProjectDetailsView());
    }

    /**
     * Initializes a container for the whole user interface.
     */
    private void initializeContentLayout() {

        contentLayout = new VerticalLayout();
        contentLayout.setMargin(false);
        contentLayout.addComponents(topBarLayout, viewContainerLayout);
        contentLayout.setSizeFull();
        contentLayout.setExpandRatio(viewContainerLayout, 1.0f);
    }

    /**
     * Initializes a layout for showing different application views.
     */
    private void initializeViewContainerLayout() {

        viewContainerLayout = new VerticalLayout();
        viewContainerLayout.setMargin(false);
        viewContainerLayout.setSizeFull();
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
            // TODO: set navigation state to user profile view name with user ID parameter
            userNameLink.addClickListener(
                    (Button.ClickListener) clickEvent -> navigator.navigateTo(""));

            settingLink = new Button("Settings", VaadinIcons.COG);
            settingLink.setStyleName("link");
            // TODO: set navigation state to setting view name
            settingLink.addClickListener(
                    (Button.ClickListener) clickEvent -> navigator.navigateTo(""));

            this.addComponents(userNameLink, settingLink);
        }
    }
}
