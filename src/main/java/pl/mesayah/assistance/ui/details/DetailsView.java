package pl.mesayah.assistance.ui.details;

import com.vaadin.navigator.View;
import pl.mesayah.assistance.Entity;

public interface DetailsView<T extends Entity> extends View {

    T getEntity();

    void enterCreateMode();

    void enterEditMode();

    void enterReadMode();
}
