package pl.mesayah.assistance.security.privilege;

import pl.mesayah.assistance.security.role.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Privilege implements Serializable, pl.mesayah.assistance.Entity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;


    public Privilege() {


    }


    public Privilege(String name) {

        this.name = name;
    }


    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }


    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Privilege privilege = (Privilege) o;
        return Objects.equals(id, privilege.id) &&
                Objects.equals(name, privilege.name);
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


    public Collection<Role> getRoles() {

        return roles;
    }


    public void setRoles(Collection<Role> roles) {

        this.roles = roles;
    }

    @Override
    public String toString() {
        return "("+ id + ") " + name;
    }
}
