package pl.mesayah.assistance;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import pl.mesayah.assistance.utils.RepositoryUtils;
import pl.mesayah.assistance.utils.ViewUtils;

import javax.annotation.PostConstruct;
import java.util.List;

public abstract class AbstractDetailsView<T extends Entity> extends VerticalLayout implements DetailsView {

    @Autowired
    private SpringNavigator navigator;

    private T entity;

    private Binder<T> dataBinder;

    private List<Component> readComponents;

    private List<Component> editComponents;

    private Button deleteButton;

    private Button confirmButton;

    private Button editButton;

    private HorizontalLayout buttonsLayout;

    public AbstractDetailsView() {

        readComponents = initializeReadComponents();
        editComponents = initializeEditComponents();

        deleteButton = initializeDeleteButton();
        deleteButton.addClickListener(clickEvent -> ((AssistanceUi) getUI()).showDeleteWindow(entity));

        confirmButton = initializeConfirmButton();
        confirmButton.addClickListener(clickEvent -> updateDetails());

        editButton = initializeEditButton();
        editButton.addClickListener(clickEvent -> navigator.navigateTo(
                ViewUtils.getDetailsViewNameFor(getEntity()) + "/" + getEntity().getId() + "/" +
                        ViewMode.EDIT_MODE.getUrlString()));

        buttonsLayout = new HorizontalLayout();

        dataBinder = initializeDataBinder();
    }

    /**
     * Initializes all read components (eg. labels) and adds them to a list. Components must be added in the order
     * of appearing in this view. These components will be later put on this view in an order they are present in the
     * list.
     *
     * @return a list of read components of this view
     */
    protected abstract List<Component> initializeReadComponents();

    /**
     * Initializes all edit components (eg. text fields) and adds them to a list. Components must be added in the order
     * of appearing in this view. These components will be later put on this view in an order they are present in the
     * list.
     *
     * @return a list of edit components of this view
     */
    protected abstract List<Component> initializeEditComponents();

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
    private void updateDetails() {

        try {
            dataBinder.writeBean(entity);
        } catch (ValidationException e) {
            e.printStackTrace();
            dataBinder.validate();
            return;
        }

        CrudRepository repository = RepositoryUtils.getRepositoryFor(entity.getClass());
        T saved = (T) repository.save(entity);

        navigator.navigateTo(ViewUtils.getDetailsViewNameFor(entity) + "/" + saved.getId());
    }

    /**
     * Creates and initializes the look of the edit button. This method doesn't set a click listener.
     *
     * @return a button object defined for edit action
     */
    protected abstract Button initializeEditButton();

    /**
     * @return an entity this view is showing details of
     */
    public T getEntity() {

        return entity;
    }

    /**
     * Initializes a data binder and sets bindings and validation for all needed field componenents.
     *
     * @return a binder with validation and bindings set up
     */
    protected abstract Binder<T> initializeDataBinder();

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
            CrudRepository<T, Long> repository = RepositoryUtils.getRepositoryFor(entity.getClass());
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
                Notification.show("No such " + ViewUtils.getDetailsViewNameFor(entity) + " in the database.", Notification.Type.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Creates and initializes default properties of empty entity.
     *
     * @return a default empty entity
     */
    protected abstract T createEmptyEntity();

    @Override
    public void enterCreateMode() {

        switchComponentsToMode(ViewMode.CREATE_MODE);

        confirmButton.setCaption("Add");

        deleteButton.setVisible(false);
    }

    /**
     * Removes all components in this view and adds only editable components.
     */
    private void switchComponentsToMode(ViewMode viewMode) {

        removeAllComponents();
        buttonsLayout.removeAllComponents();

        List<Component> components;

        if (viewMode == ViewMode.EDIT_MODE || viewMode == ViewMode.CREATE_MODE) {

            buttonsLayout.addComponents(confirmButton, deleteButton);
            components = editComponents;
        } else {

            buttonsLayout.addComponents(editButton, deleteButton);
            components = readComponents;
        }

        addComponent(buttonsLayout);

        for (Component c : components) {
            addComponent(c);
        }
    }

    /**
     * Sets values of all readable components to corresponding properties of the entity.
     */
    protected abstract void setReadComponentsValues();

    @Override
    public void enterReadMode() {

        switchComponentsToMode(ViewMode.READ_MODE);

        setReadComponentsValues();

        deleteButton.setVisible(true);
    }

    @Override
    public void enterEditMode() {

        switchComponentsToMode(ViewMode.EDIT_MODE);

        confirmButton.setCaption("Confirm");

        deleteButton.setVisible(true);
    }
}
