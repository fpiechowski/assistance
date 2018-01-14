package pl.mesayah.assistance;

import com.vaadin.navigator.View;
import com.vaadin.ui.Grid;

import java.util.Collection;

public interface ListView<T extends Entity> extends View {

    Collection<T> fetchDataSet();

    Grid<T> initializeListing();
}
