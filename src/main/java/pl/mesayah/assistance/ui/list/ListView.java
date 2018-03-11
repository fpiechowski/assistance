package pl.mesayah.assistance.ui.list;

import com.vaadin.data.HasValue;
import com.vaadin.navigator.View;
import pl.mesayah.assistance.Entity;

public interface ListView<T extends Entity> extends View {


    void onNameFilterValueChange(HasValue.ValueChangeEvent<String> stringValueChangeEvent);
}
