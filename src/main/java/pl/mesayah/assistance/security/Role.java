package pl.mesayah.assistance.security;

import pl.mesayah.assistance.user.User;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Role {

    public static final String SUPER_ADMIN = "SUPER_ADMIN";

    public static final String PROJECT_MANAGER = "PROJECT_MANAGER";

    public static final String DEVELOPER = "DEVELOPER";

    public static final String CLIENT = "CLIENT";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_privilege",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id")
    )
    private Collection<Privilege> privileges;

    public Role() {

    }

    public Role(String name) {

        this.name = name;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Collection<User> getUsers() {

        return users;
    }

    public void setUsers(Collection<User> users) {

        this.users = users;
    }

    public Collection<Privilege> getPrivileges() {

        return privileges;
    }

    public void setPrivileges(Collection<Privilege> privileges) {

        this.privileges = privileges;
    }
}
