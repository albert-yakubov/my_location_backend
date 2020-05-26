package com.stepashka.my_location.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stepashka.my_location.logging.Loggable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Loggable
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roleid;

    @Column(nullable = false,
            unique = true)
    private String name;

    @OneToMany(mappedBy = "role",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("role")
    private List<UserRoles> userroles = new ArrayList<>();

    public Role(String admin){}

    public Role(long roleid, String name, List<UserRoles> userroles) {
        this.roleid = roleid;
        this.name = name.toUpperCase();
        this.userroles = userroles;
    }

    public long getRoleid() {
        return roleid;
    }

    public void setRoleid(long roleid) {
        this.roleid = roleid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserRoles> getUserroles() {
        return userroles;
    }

    public void setUserroles(List<UserRoles> userroles) {
        this.userroles = userroles;
    }
}
