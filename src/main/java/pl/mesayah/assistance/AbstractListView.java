package pl.mesayah.assistance;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mesayah.assistance.utils.ViewUtils;

import java.util.Collection;
import java.util.Set;

public abstract class AbstractListView<T extends Entity> extends VerticalLayout implements ListView<T> {

    @Autowired
    private SpringNavigator navigator;

    private ListDataProvider<T> dataProvider;

    private Grid<T> listing;

    private Button editButton;

    private Button deleteButton;

    public AbstractListView() {

        dataProvider = DataProvider.ofCollection(fetchDataSet());

        listing = initializeListing();
        listing.setDataProvider(dataProvider);
        listing.setSelectionMode(Grid.SelectionMode.SINGLE);

        editButton = initializeEditButton();
        editButton.addClickListener(clickEvent -> {
            Set<T> selectedSet = listing.getSelectedItems();
            if (!(selectedSet.isEmpty())) {
                T selected = selectedSet.iterator().next();
                navigator.navigateTo(ViewUtils.getDetailsViewNameFor(selected.getClass()) + "/" +
                        selected.getId() + "/" + ViewMode.EDIT_MODE.getUrlString());
            }
        });

        deleteButton = initializeDeleteButton();
        deleteButton.addClickListener(clickEvent -> {
            Set<T> selectedSet = listing.getSelectedItems();
            if (!(selectedSet.isEmpty())) {
                T selected = selectedSet.iterator().next();
                ((AssistanceUi) getUI()).showDeleteWindow(selected);
            }
        });
    }

    @Override
    public abstract Collection<T> fetchDataSet();

    @Override
    public abstract Grid<T> initializeListing();

    protected abstract Button initializeEditButton();

    protected abstract Button initializeDeleteButton();

    protected abstract boolean isGridEditable();

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {


    }
}
