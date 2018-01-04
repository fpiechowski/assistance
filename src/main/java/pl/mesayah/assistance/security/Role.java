package pl.mesayah.assistance.security;

public enum Role {

    CLIENT("Client"),
    DEVELOPER("Developer"),
    PROJECT_MANAGER("Project Manager"),
    ADMIN("Administrator");

    private final String name;

    Role(String name) {

        this.name = name;
    }

    @Override
    public String toString() {

        return name;
    }
}
