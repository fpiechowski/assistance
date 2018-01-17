package pl.mesayah.assistance.issue;

import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mesayah.assistance.project.Project;
import pl.mesayah.assistance.project.ProjectService;
import pl.mesayah.assistance.security.Role;
import pl.mesayah.assistance.security.RoleService;
import pl.mesayah.assistance.security.SecurityUtils;
import pl.mesayah.assistance.task.Task;
import pl.mesayah.assistance.ui.AbstractDetailsView;
import pl.mesayah.assistance.user.User;
import pl.mesayah.assistance.user.UserService;
import pl.mesayah.assistance.utils.ViewUtils;

import java.time.LocalDate;
import java.util.*;

@SpringView(name = IssueDetailsView.VIEW_NAME)
public class IssueDetailsView extends AbstractDetailsView<Issue> {

    public static final String VIEW_NAME = "issue";

    private TextField nameTextField;

    private TextArea descriptionTextArea;

    private NativeSelect<Task.Status> statusNativeSelect;

    private TwinColSelect<User> assignessTwinColSelect;

    private NativeSelect<Task.Priority> priorityNativeSelect;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    private Label nameLabel;

    private Label descriptionLabel;

    private Button projectButton;

    private Button reportingUserButton;

    private Label reportDateLabel;

    private Label statusLabel;

    private ListSelect<User> assigneesListSelect;

    private Label priorityLabel;

    private ComboBox<Project> projectComboBox;

    @Autowired
    private ProjectService projectService;

    public IssueDetailsView() {

        setSizeFull();
    }

    @Override
    protected List<Component> initializeReadComponents() {

        nameLabel = new Label();
        nameLabel.setCaption("Name:");

        descriptionLabel = new Label();
        descriptionLabel.setCaption("Description:");

        projectButton = new Button(VaadinIcons.NOTEBOOK);
        projectButton.setStyleName("link");

        Layout projectLayout = new HorizontalLayout(projectButton);
        projectLayout.setCaption("Project:");

        reportingUserButton = new Button(VaadinIcons.USER);
        reportingUserButton.setStyleName("link");
        Layout reportingUserLayout = new HorizontalLayout(reportingUserButton);
        projectLayout.setCaption("Reporting User:");

        reportDateLabel = new Label();
        reportDateLabel.setCaption("Date:");

        statusLabel = new Label();
        statusLabel.setCaption("Status:");

        assigneesListSelect = new ListSelect<>("Assignees:");

        priorityLabel = new Label();
        priorityLabel.setCaption("Priority:");

        HorizontalLayout nameStatusPriorityLabel = new HorizontalLayout(nameLabel, statusLabel, priorityLabel);
        HorizontalLayout projectUserDateLayout = new HorizontalLayout(projectLayout, reportingUserLayout, reportDateLabel);

        return Arrays.asList(
                nameStatusPriorityLabel,
                projectUserDateLayout,
                descriptionLabel
        );
    }

    @Override
    protected List<Component> initializeEditComponents() {

        VerticalLayout container = new VerticalLayout();
        container.setSizeFull();
        container.setMargin(false);

        nameTextField = new TextField("Name:");
        nameTextField.setWidth("100%");

        projectComboBox = new ComboBox<>("Project:");

        descriptionTextArea = new TextArea("Description:");
        descriptionTextArea.setSizeFull();

        statusNativeSelect = new NativeSelect<>("Status:", Arrays.asList(Task.Status.values()));
        statusNativeSelect.setEmptySelectionAllowed(false);
        statusNativeSelect.setSelectedItem(Task.Status.WAITING);
        statusNativeSelect.setWidth("200px");

        assignessTwinColSelect = new TwinColSelect<>("Assignees:");
        assignessTwinColSelect.setHeight("100%");

        priorityNativeSelect = new NativeSelect<>("Priority:", Arrays.asList(Task.Priority.values()));
        priorityNativeSelect.setEmptySelectionAllowed(false);
        priorityNativeSelect.setSelectedItem(Task.Priority.MEDIUM);
        priorityNativeSelect.setWidth("200px");

        HorizontalLayout nameStatusPriorityLayout = new HorizontalLayout(nameTextField, projectComboBox, statusNativeSelect, priorityNativeSelect);
        nameStatusPriorityLayout.setWidth("100%");
        nameStatusPriorityLayout.setExpandRatio(nameTextField, 1.0f);

        HorizontalLayout descriptionAssigneesLayout = new HorizontalLayout(descriptionTextArea, assignessTwinColSelect);
        descriptionAssigneesLayout.setSizeFull();
        descriptionAssigneesLayout.setExpandRatio(descriptionTextArea, 1.0f);

        container.addComponents(nameStatusPriorityLayout, descriptionAssigneesLayout);
        container.setExpandRatio(descriptionAssigneesLayout, 1.0f);

        return Arrays.asList(
                container
        );
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
    protected Binder<Issue> initializeDataBinder() {

        Binder<Issue> binder = new Binder<>(Issue.class);
        binder.forField(nameTextField)
                .withValidator(name -> name.length() > 0, "Name can't be empty.")
                .bind(Issue::getName, Issue::setName);
        binder.forField(projectComboBox)
                .withValidator(Objects::nonNull, "An issue must be reported for a certain project.")
                .bind(Issue::getProject, Issue::setProject);
        binder.forField(descriptionTextArea)
                .bind(Issue::getDescription, Issue::setDescription);
        binder.forField(statusNativeSelect)
                .withValidator(Objects::nonNull, "You have to set a status.")
                .bind(Issue::getStatus, Issue::setStatus);
        binder.forField(assignessTwinColSelect)
                .bind(Issue::getAssigneeUsers, Issue::setAssigneeUsers);
        binder.forField(priorityNativeSelect)
                .withValidator(Objects::nonNull, "You have to set a priority.")
                .bind(Issue::getPriority, Issue::setPriority);
        return binder;
    }

    @Override
    protected void loadData() {


        Set<User> possibleAssignees = new HashSet<>();
        possibleAssignees.addAll(roleService.findByName(Role.PROJECT_MANAGER).getUsers());
        possibleAssignees.addAll(roleService.findByName(Role.DEVELOPER).getUsers());
        possibleAssignees.addAll(roleService.findByName(Role.SUPER_ADMIN).getUsers());
        assignessTwinColSelect.setItems(possibleAssignees);

        List<Project> projects = projectService.findAll();
        projectComboBox.setItems(projects);
    }

    @Override
    protected Issue createEmptyEntity() {

        Issue created = new Issue();

        User reporting = userService.findByUsername(SecurityUtils.getCurrentUserUsername());
        created.setReportingUser(reporting);

        created.setReportDate(LocalDate.now());
        return created;
    }

    @Override
    protected void setReadComponentsValues() {

        nameLabel.setValue(getEntity().getName());
        descriptionLabel.setValue(getEntity().getDescription());
        projectButton.setCaption(getEntity().getProject().getName());
        String projectUrl = ViewUtils.getDetailsViewNameFor(getEntity().getProject().getClass()) + "/" +
                getEntity().getProject().getId();
        projectButton.addClickListener(clickEvent -> getNavigator().navigateTo(projectUrl));
        reportingUserButton.setCaption(getEntity().getReportingUser().getUsername());
        User reporter = getEntity().getReportingUser();
        String userUrl = ViewUtils.getDetailsViewNameFor(reporter.getClass()) + "/" + reporter.getId();
        reportingUserButton.addClickListener(clickEvent -> getNavigator().navigateTo(userUrl));
        reportDateLabel.setValue(getEntity().getReportDate().toString());
        statusLabel.setValue(getEntity().getStatus().name());
        assigneesListSelect.setItems(getEntity().getAssigneeUsers());
        priorityLabel.setValue(getEntity().getPriority().name());
    }
}
