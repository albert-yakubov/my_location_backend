package com.stepashka.my_location.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stepashka.my_location.logging.Loggable;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Loggable
@Entity
@Table(name = "useremails",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"userid", "useremail"})})
public class Useremail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long useremailid;

    @Column(nullable = false)
    @Email
    private String useremail;

    @ManyToOne
    @JoinColumn(name = "userid",
            nullable = false)
    @JsonIgnoreProperties("useremails")
    private User user;

    public Useremail(User newUser, String useremail){}

    public Useremail(long useremailid, @Email String useremail, User user) {
        this.useremailid = useremailid;
        this.useremail = useremail;
        this.user = user;
    }

    public long getUseremailid() {
        return useremailid;
    }

    public void setUseremailid(long useremailid) {
        this.useremailid = useremailid;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
