package pl.mesayah.assistance.ui;

public enum ViewMode {

    CREATE_MODE("new"),
    EDIT_MODE("edit"),
    READ_MODE("read");

    private String urlString;

    ViewMode(String urlString) {

        this.urlString = urlString;
    }

    public String getUrlString() {

        return urlString;
    }
}
