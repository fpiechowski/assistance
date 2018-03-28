package pl.mesayah.assistance.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.server.Page;
import com.vaadin.ui.*;

@Theme("mytheme")
public class RelaseNotesView extends VerticalLayout {

    private String buildVersion = "0.1 Alpha";
    private String relaseDate = "Wed, Mar 07, 2018, 10:40:35 PM UTC";

    private String[][] notes = {
            {
                    "07.03.2017 Ostry", "- Added specified class name to database connection properties." +
                    "<br />    Add: spring.datasource.driver-class-name=com.mysql.jdbc.Driver to application-database.properties." +
                    "<br />- Created form for relaseNotes" +
                    "<br />- Created logout button" +
                    "<br />- Set styled for Navigation Bar" +
                    "<br />- Remove Remember Password checkbox" +
                    "<br />- Add manually added build version and relase date."
            }

    };

    private String[][] bugs = {
            {
                    "Ostry", ""
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


        VerticalLayout general = new VerticalLayout();
        HorizontalLayout accordions = new HorizontalLayout();

        Accordion notesView = new Accordion();
        Accordion knowsbugs = new Accordion();

        notesView.setSizeFull();
        knowsbugs.setSizeFull();


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


        setSizeFull();
        setMargin(false);
        setSpacing(false);
        general.setSizeFull();
        general.setSpacing(false);
        general.setMargin(false);

        accordions.setSizeFull();
        back.setHeightUndefined();

        general.addComponents(accordions);
        accordions.addComponents(notesView, knowsbugs);
        addComponents(general,back);
    }


    public String getBuildVersion() {

        return buildVersion;
    }


    public String getRelaseDate() {

        return relaseDate;
    }
}
