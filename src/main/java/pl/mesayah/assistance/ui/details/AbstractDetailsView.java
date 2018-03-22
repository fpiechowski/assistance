package pl.mesayah.assistance.ui.details;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import pl.mesayah.assistance.Entity;
import pl.mesayah.assistance.Repositories;
import pl.mesayah.assistance.ui.AssistanceUi;
import pl.mesayah.assistance.ui.ViewMode;
import pl.mesayah.assistance.ui.list.ListViews;

import javax.annotation.PostConstruct;
import java.util.List;

public abstract class AbstractDetailsView<T extends Entity> extends VerticalLayout implements DetailsView<T> {

    @Autowired
    private SpringNavigator navigator;

    private T entity;

    private Binder<T> dataBinder;

    private List<Component> editComponents;

    private Button deleteButton;

    private Button confirmButton;

    private Button editButton;

    private Button backButton;

    private HorizontalLayout buttonsLayout;

    private Button cancelButton;


    public AbstractDetailsView() {

        setId("abstract-container");

        setHeight("100%");

        editComponents = initializeEditComponents();

        cancelButton = new Button("Cancel", VaadinIcons.ARROWS_CROSS);
        cancelButton.addClickListener(clickEvent -> {
            navigator.navigateTo(
                    DetailsViews.getDetailsViewNameFor(getEntity().getClass()) + "/" + getEntity().getId());
        });

        backButton = new Button(VaadinIcons.ARROW_LEFT);
        backButton.addClickListener(clickEvent -> {
            navigator.navigateTo(
                    ListViews.getListViewNameFor(getEntity().getClass()));
        });

        deleteButton = initializeDeleteButton();
        deleteButton.addClickListener(clickEvent -> ((AssistanceUi) getUI()).showDeleteWindow(entity));

        confirmButton = initializeConfirmButton();
        confirmButton.addClickListener(clickEvent -> updateDetails());

        editButton = initializeEditButton();
        editButton.addClickListener(clickEvent -> navigator.navigateTo(
                DetailsViews.getDetailsViewNameFor(getEntity().getClass()) + "/" + getEntity().getId() + "/" +
                        ViewMode.EDIT_MODE.getUrlString()));

        buttonsLayout = new HorizontalLayout();

        dataBinder = initializeDataBinder();
    }


    /**
     * Initializes all edit components (eg. text fields) and adds them to a list. Components must be added in the order
     * of appearing in this view. These components will be later put on this view in an order they are present in the
     * list.
     *
     * @return a list of edit components of this view
     */
    protected abstract List<Component> initializeEditComponents();


    /**
     * @return an entity this view is showing details of
     */
    public T getEntity() {

        return entity;
    }


    /**
     * Creates and initializes the look of the delete button. This method doesn't set a click listener.
     *
     * @return a button object defined for edit action
     */
    protected abstract Button initializeDeleteButton();


    /**
     * Creates and initializes the look of the confirm button. This method doesn't set a click listener.
     *
     * @return a button object defined for confirm action
     */
    protected abstract Button initializeConfirmButton();


    /**
     * Validates an input made in edit components and saves changes made to the entity if no validation errors occurs.
     */
    public void updateDetails() {

        try {
            dataBinder.writeBean(entity);
        } catch (ValidationException e) {
            e.printStackTrace();
            dataBinder.validate();
            return;
        }

        CrudRepository repository = Repositories.getRepositoryFor(entity.getClass());
        T saved = (T) repository.save(entity);

        navigator.navigateTo(DetailsViews.getDetailsViewNameFor(entity.getClass()) + "/" + saved.getId());
    }


    /**
     * Creates and initializes the look of the edit button. This method doesn't set a click listener.
     *
     * @return a button object defined for edit action
     */
    protected abstract Button initializeEditButton();


    /**
     * Initializes a data binder and sets bindings and validation for all needed field componenents.
     *
     * @return a binder with validation and bindings set up
     */
    protected abstract Binder<T> initializeDataBinder();


    @Override
    public void enterCreateMode() {

        switchComponentsToMode(ViewMode.CREATE_MODE);

        confirmButton.setCaption("Add");

        deleteButton.setVisible(false);
    }


    @Override
    public void enterEditMode() {

        switchComponentsToMode(ViewMode.EDIT_MODE);

        confirmButton.setCaption("Confirm");

        deleteButton.setVisible(true);
    }


    @Override
    public void enterReadMode() {

        switchComponentsToMode(ViewMode.READ_MODE);

        deleteButton.setVisible(true);
    }


    public SpringNavigator getNavigator() {

        return navigator;
    }


    /**
     * Loads data from persistence for populating components. For example if you need to fetch all tasks to put them
     * into a grid or something you can fetch the data and set grid's items here. The reason why this method exists
     * is because you cannot use autowired beans in constructors.
     */
    @PostConstruct
    protected abstract void loadData();


    /**
     * Retrieves URL parameters and chooses which mode (read or edit) to enter.
     *
     * @param event a view change event occured during entering this view
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        String[] parameters = event.getParameters().split("/");

        if (parameters[0].equals(ViewMode.CREATE_MODE.getUrlString())) {

            entity = createEmptyEntity();
            dataBinder.readBean(entity);
            enterCreateMode();
        } else if (StringUtils.isNumeric(parameters[0])) {

            Long entityId = Long.parseLong(parameters[0]);
            entity = createEmptyEntity();
            CrudRepository<T, Long> repository = Repositories.getRepositoryFor(entity.getClass());
            entity = repository.findOne(entityId);

            if (entity != null) {

                dataBinder.readBean(entity);

                if (parameters.length == 2) {

                    if (parameters[1].equals(ViewMode.EDIT_MODE.getUrlString())) {

                        enterEditMode();
                    } else if (parameters[1].equals(ViewMode.READ_MODE.getUrlString())) {

                        enterReadMode();
                    }
                } else {

                    enterReadMode();
                }
            } else {

                entity = createEmptyEntity();
                Notification.show("No such " + DetailsViews.getDetailsViewNameFor(entity.getClass()) + " in the database.", Notification.Type.ERROR_MESSAGE);
            }
        }
    }


    /**
     * Creates and initializes default properties of empty entity.
     *
     * @return a default empty entity
     */
    protected abstract T createEmptyEntity();


    /**
     * Removes all components in this view and adds only editable components.
     */
    private void switchComponentsToMode(ViewMode viewMode) {

        removeAllComponents();
        buttonsLayout.removeAllComponents();


        if (viewMode == ViewMode.CREATE_MODE) {

            buttonsLayout.addComponents(backButton, confirmButton, deleteButton);
            editComponents.stream().forEach(c -> c.setEnabled(true));
        } else if (viewMode == ViewMode.EDIT_MODE) {

            buttonsLayout.addComponents(cancelButton, confirmButton, deleteButton);
            editComponents.stream().forEach(c -> c.setEnabled(true));
        } else if (viewMode == ViewMode.READ_MODE) {

            buttonsLayout.addComponents(backButton, editButton, deleteButton);
            editComponents.stream().forEach(c -> c.setEnabled(false));
        }

        addComponent(buttonsLayout);

        VerticalLayout container = new VerticalLayout();
        container.setMargin(false);
        container.setSizeUndefined();
        for (Component c : editComponents) {
            container.addComponent(c);
        }

        addComponent(container);

        setExpandRatio(container, 1.0f);
    }
}
