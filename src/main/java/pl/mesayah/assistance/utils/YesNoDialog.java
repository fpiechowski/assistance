package pl.mesayah.assistance.utils;

import com.vaadin.ui.*;

public class YesNoDialog extends Window {

    private Button yesButton;

    private Button noButton;

    private Label messageLabel;

    private VerticalLayout contentLayout;

    private HorizontalLayout buttonsLayout;

    public YesNoDialog(String caption, String message, Button.ClickListener onYesClickListener, Button.ClickListener onNoClickListener) {

        this(caption, message, onYesClickListener);

        noButton.addClickListener(onNoClickListener);
    }

    public YesNoDialog(String caption, String message, Button.ClickListener onYesClickListener) {

        super(caption);

        messageLabel = new Label(message);

        yesButton = new Button("Yes", onYesClickListener);
        yesButton.addClickListener((Button.ClickListener) clickEvent -> close());

        noButton = new Button("No", (Button.ClickListener) clickEvent -> close());

        buttonsLayout = new HorizontalLayout();
        buttonsLayout.addComponents(yesButton, noButton);

        contentLayout = new VerticalLayout();
        contentLayout.addComponents(messageLabel, buttonsLayout);

        setContent(contentLayout);
    }
}
