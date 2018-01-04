package pl.mesayah.assistance.utils;

import com.vaadin.ui.*;

/**
 * An utility dialog window with a title caption, a text message and two buttons: Yes and No.
 */
public class YesNoDialog extends Window {

    /**
     * A button with "Yes" caption.
     */
    private Button yesButton;

    /**
     * A button with "No" caption.
     */
    private Button noButton;

    /**
     * A label with a text message.
     */
    private Label messageLabel;

    /**
     * A layout for holding all other components in this window.
     */
    private VerticalLayout contentLayout;

    /**
     * A layout holding Yes and No buttons horizontally.
     */
    private HorizontalLayout buttonsLayout;

    /**
     * Constructs a dialog with a specific action defined for both buttons.
     *
     * @param caption            a text caption for title of this window
     * @param message            a text message information of this window
     * @param onYesClickListener a click listener for Yes button event
     * @param onNoClickListener  a click listener for No button event
     */
    public YesNoDialog(String caption, String message, Button.ClickListener onYesClickListener, Button.ClickListener onNoClickListener) {

        this(caption, message, onYesClickListener);

        noButton.addClickListener(onNoClickListener);
    }

    /**
     * Constructs a dialog with defined action for only Yes button. No button has default action of closing this window.
     *
     * @param caption            a text caption for title of this window
     * @param message            a text message information of this window
     * @param onYesClickListener a click listener for Yes button event
     */
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

        contentLayout.setComponentAlignment(buttonsLayout, Alignment.MIDDLE_CENTER);

        center();

        setContent(contentLayout);
    }
}
