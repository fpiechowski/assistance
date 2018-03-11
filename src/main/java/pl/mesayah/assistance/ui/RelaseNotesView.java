package pl.mesayah.assistance.ui;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import pl.mesayah.assistance.security.LoginForm;

import java.util.Date;


public class RelaseNotesView extends HorizontalLayout {

    private String buildVersion = "0.1 Alpha";
    private String relaseDate = "Wed, Mar 07, 2018, 10:40:35 PM UTC";

    private String[][] notes = {
            {
                    "07.03.2017 Ostry", "- Added specified class name to database connection properties." +
                    "<br />    Add: spring.datasource.driver-class-name=com.mysql.jdbc.Driver to application-database.properties." +
                    "<br />- Created form for relaseNotes"+
                    "<br />- Created logout button" +
                    "<br />- Set styled for Navigation Bar" +
                    "<br />- Remove Remember Password checkbox" +
                    "<br />- Add manually added build version and relase date."
            }

    };

    private String[][] bugs = {
            {
                    "Ostry", "- After click Previous Page on WEB, app redirect to /#!team/1"
            },
            {
                    "Szyku", ""
            },
            {
                    "Filip", ""
            },
            {
                    "Łukasz", ""
            }
    };

    public RelaseNotesView() {
        Button back = new Button("Powrót", event ->
                Page.getCurrent().reload()
        );
        Accordion notesView = new Accordion();
        notesView.setHeight(100.0f, Unit.PERCENTAGE);
        notesView.setWidth(50.0f, Unit.PERCENTAGE);
        for (String[] n : notes) {
            String[] w = n;
            Label label = new Label();
            label.setCaptionAsHtml(true);
            label.setCaption(w[1]);
            label.setWidth(100.0f, Unit.PERCENTAGE);

            VerticalLayout layout = new VerticalLayout(label);
            layout.setMargin(true);
            notesView.addTab(layout, w[0]);
        }
        Accordion knowsbugs = new Accordion();
        knowsbugs.setHeight(100.0f, Unit.PERCENTAGE);
        knowsbugs.setWidth(50.0f, Unit.PERCENTAGE);
        for (String[] n : bugs) {
            String[] w = n;
            Label label = new Label();
            label.setCaptionAsHtml(true);
            label.setCaption(w[1]);
            label.setWidth(100.0f, Unit.PERCENTAGE);

            VerticalLayout layout = new VerticalLayout(label);
            layout.setMargin(true);
            knowsbugs.addTab(layout, w[0]);
        }
        setHeight("100%");
        addComponents(back,notesView, knowsbugs);
    }

    public String getBuildVersion() {
        return buildVersion;
    }

    public String getRelaseDate() {
        return relaseDate;
    }
}
