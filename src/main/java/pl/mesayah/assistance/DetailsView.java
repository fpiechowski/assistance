package pl.mesayah.assistance;

import com.vaadin.navigator.View;

public interface DetailsView extends View {

    void enterCreateMode();

    void enterReadMode();

    void enterEditMode();
}
