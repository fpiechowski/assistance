package pl.mesayah.assistance.security.role;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import pl.mesayah.assistance.security.privilege.Privilege;
import pl.mesayah.assistance.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Role implements Serializable, pl.mesayah.assistance.Entity {

    public static final String SUPER_ADMIN = "SUPER_ADMIN";

    public static final String PROJECT_MANAGER = "PROJECT_MANAGER";

    public static final String DEVELOPER = "DEVELOPER";

    public static final String CLIENT = "CLIENT";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
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


    @Override
    public int hashCode() {

        return Objects.hash(id, name, privileges);
    }


    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(name, role.name) &&
                Objects.equals(privileges, role.privileges);
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
