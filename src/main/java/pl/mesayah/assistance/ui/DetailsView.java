package pl.mesayah.assistance.ui;

import com.vaadin.navigator.View;
import pl.mesayah.assistance.Entity;

public interface DetailsView<T extends Entity> extends View {

    T getEntity();

    void enterCreateMode();

    void enterReadMode();

    void enterEditMode();
}
