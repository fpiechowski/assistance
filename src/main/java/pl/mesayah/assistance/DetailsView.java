package pl.mesayah.assistance;

import com.vaadin.navigator.View;

public interface DetailsView<T extends Entity> extends View {

    T getEntity();

    void enterCreateMode();

    void enterReadMode();

    void enterEditMode();
}
