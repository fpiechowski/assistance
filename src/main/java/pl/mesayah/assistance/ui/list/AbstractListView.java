package pl.mesayah.assistance.ui.list;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.data.HasValue;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mesayah.assistance.AbstractFilterableEntity;
import pl.mesayah.assistance.Filterable;
import pl.mesayah.assistance.security.SecurityUtils;
import pl.mesayah.assistance.security.role.Role;
import pl.mesayah.assistance.ui.AssistanceUi;
import pl.mesayah.assistance.ui.ViewMode;
import pl.mesayah.assistance.utils.DetailsViews;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public abstract class AbstractListView<T extends AbstractFilterableEntity> extends VerticalLayout implements ListView<T> {

    @Autowired
    private SpringNavigator navigator;

    private ListDataProvider<T> dataProvider;

    private T entity;

    private Grid<T> listing;

    private HorizontalLayout buttonsLayout;

    private List<Button> additionalButtons;

    private Button newButton;

    private Button editButton;

    private Button detailsButton;

    private Button deleteButton;

    private TextField nameFilterTextField;


    public AbstractListView() {

        entity = createEmptyEntity();

        additionalButtons = new ArrayList<>();
        additionalButtons = initializeAdditionalButtons();

        listing = initializeListing();
        listing.getEditor().setEnabled(isGridEditable());
        listing.setSelectionMode(Grid.SelectionMode.SINGLE);
        listing.addSelectionListener(selectionEvent -> {


            if (selectionEvent.getAllSelectedItems().isEmpty()) {
                editButton.setEnabled(false);
                deleteButton.setEnabled(false);
            } else {
                if (selectionEvent.getAllSelectedItems().size() > 1 && editButton.getDescription() == "t") {
                    editButton.setEnabled(false);
                    deleteButton.setEnabled(true);
                } else if(editButton.getDescription() == "t"){
                    editButton.setEnabled(true);
                    deleteButton.setEnabled(true);
                }
            }

            

        });
        listing.deselectAll();


        newButton = initializeNewButton();
        newButton.addStyleName(MaterialTheme.BUTTON_FRIENDLY);
        newButton.addClickListener(clickEvent -> navigator.navigateTo(DetailsViews.getDetailsViewNameFor(entity.getClass()) + "/" + ViewMode.CREATE_MODE.getUrlString()));

        editButton = initializeEditButton();
        editButton.addStyleName(MaterialTheme.BUTTON_PRIMARY);
        editButton.addClickListener(clickEvent -> {
            Set<T> selectedSet = listing.getSelectedItems();
            if (!(selectedSet.isEmpty())) {
                T selected = selectedSet.iterator().next();
                navigator.navigateTo(DetailsViews.getDetailsViewNameFor(selected.getClass()) + "/" +
                        selected.getId() + "/" + ViewMode.EDIT_MODE.getUrlString());
            }
        });

        deleteButton = initializeDeleteButton();
        deleteButton.addStyleName(MaterialTheme.BUTTON_DANGER);
        deleteButton.addClickListener(clickEvent -> {
            Set<T> selectedSet = listing.getSelectedItems();
            if (!(selectedSet.isEmpty())) {
                ((AssistanceUi) getUI()).showDeleteWindow(selectedSet);
            }
        });

        detailsButton = initializeDetailsButton();
        detailsButton.addClickListener(clickEvent -> {
            Set<T> selectedSet = listing.getSelectedItems();
            if (!(selectedSet.isEmpty())) {
                T selected = selectedSet.iterator().next();
                navigator.navigateTo(DetailsViews.getDetailsViewNameFor(selected.getClass()) + "/" +
                        selected.getId() + "/" + ViewMode.READ_MODE.getUrlString());
            }
        });

        editButton.setEnabled(false);
        deleteButton.setEnabled(false);

        nameFilterTextField = new TextField();
        nameFilterTextField.setPlaceholder("Search...");
        nameFilterTextField.setWidth("100%");
        nameFilterTextField.addValueChangeListener(this::onNameFilterValueChange);

        setSizeFull();
    }


    protected abstract T createEmptyEntity();

    protected abstract List<Button> initializeAdditionalButtons();


    protected abstract Grid<T> initializeListing();


    protected abstract boolean isGridEditable();


    protected abstract Button initializeNewButton();


    protected abstract Button initializeEditButton();


    protected abstract Button initializeDeleteButton();


    protected abstract Button initializeDetailsButton();


    @PostConstruct
    protected ListDataProvider<T> initializeDataProvider() {

        dataProvider = new ListDataProvider<>(fetchDataSet());
        listing.setDataProvider(dataProvider);
        return dataProvider;
    }


    protected abstract Collection<T> fetchDataSet();


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        buttonsLayout = new HorizontalLayout(newButton, detailsButton, editButton, deleteButton);
        if (additionalButtons != null) {
            for (Button b : additionalButtons) {
                buttonsLayout.addComponent(b);
            }
        }

        buttonsLayout.addComponent(nameFilterTextField);
        buttonsLayout.setWidth("100%");
        buttonsLayout.setExpandRatio(nameFilterTextField, 1.0f);

        addComponents(buttonsLayout, listing);
        setExpandRatio(listing, 1.0f);
    }


    @Override
    public void onNameFilterValueChange(HasValue.ValueChangeEvent<String> stringValueChangeEvent) {

        ListDataProvider<T> dataProvider = (ListDataProvider<T>) listing.getDataProvider();
        dataProvider.setFilter(Filterable::getTextRepresentation, s -> caseInsensitiveContains(s, stringValueChangeEvent.getValue()));
    }


    private Boolean caseInsensitiveContains(String where, String what) {

        return where.toLowerCase().contains(what.toLowerCase());
    }
}
