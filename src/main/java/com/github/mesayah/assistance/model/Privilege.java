package com.github.mesayah.assistance.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A benefit of possibility to perform a specific operation.
 */
@Entity
public class Privilege implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "privilege_id")
    private long id;
    /**
     * Name of this privelege describing actions it permits.
     */
    private String name;

    public Privilege() {


    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }
}
