package pl.mesayah.assistance;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

@SpringUI
public class AssistanceUi extends UI {

    private Navigator navigator;

    private HorizontalLayout topBarLayout;

    private VerticalLayout viewContainer;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        topBarLayout = new HorizontalLayout();
        topBarLayout.setWidth("100%");
        Layout navigationLayout = new NavigationLayout();
        Layout userInfoLayout = new UserInfoLayout();
        topBarLayout.addComponents(userInfoLayout, navigationLayout);
        topBarLayout.setExpandRatio(navigationLayout, 1.0f);

        viewContainer = new VerticalLayout();
        viewContainer.setMargin(false);

        VerticalLayout content = new VerticalLayout();
        content.setMargin(false);
        content.addComponents(topBarLayout, viewContainer);
        content.setSizeFull();

        setContent(content);

        navigator = new Navigator(this, viewContainer);
    }

    private class NavigationLayout extends HorizontalLayout {

        private List<Button> navigationButtons;

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
                    (Button.ClickListener) clickEvent -> navigator.navigateTo("/project"));
            navigationButtons.add(projectsButton);

            Button issuesButton = new Button("Issues");
            issuesButton.addClickListener(
                    (Button.ClickListener) clickEvent -> navigator.navigateTo("/issue"));
            navigationButtons.add(issuesButton);

            this.addComponents(homeButton, tasksButton, projectsButton, issuesButton);

            for (Button b : navigationButtons) {
                b.setStyleName("link");
                this.setComponentAlignment(b, Alignment.MIDDLE_CENTER);
            }
        }
    }

    private class UserInfoLayout extends VerticalLayout {

        private Button settingLink;

        private Button userNameLink;

        public UserInfoLayout() {

            this.setWidth("-1px");
            this.setHeight("-1px");
            this.setMargin(new MarginInfo(false, true));
            this.setId("userInfoLayout");

            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            userNameLink = new Button(username);
            userNameLink.setStyleName("link");

            settingLink = new Button("Settings", VaadinIcons.COG);
            settingLink.setStyleName("link");

            this.addComponents(userNameLink, settingLink);
        }
    }
}
