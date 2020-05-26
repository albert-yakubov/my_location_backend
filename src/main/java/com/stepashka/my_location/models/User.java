package com.stepashka.my_location.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stepashka.my_location.logging.Loggable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Loggable
@Entity
@Table(name = "users")
public class User extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;
    @Column(nullable = false)
    private String profilepicture;

    @Column(nullable = false,
            unique = true)
    private String username;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false,
            unique = true)
    @Email
    private String primaryemail;
    @Column(nullable = false,
            updatable = false)
    private String firstname;
    @Column(nullable = false,
            updatable = false)
    private String lastname;
    @Column(nullable = false,
            updatable = false)
    private String mini_bio;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private Double ulatitude;
    @Column(nullable = false)
    private Double ulongitude;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<UserRoles> userroles = new ArrayList<>();

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private List<Useremail> useremails = new ArrayList<>();

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private List<PostedMaps> postedMaps = new ArrayList<>();

    public User(){}

    public User(long userid, String profilepicture, String username, String password, @Email String primaryemail, String firstname, String lastname, String mini_bio, String location, Double ulatitude, Double ulongitude, List<UserRoles> userroles, List<Useremail> useremails, List<PostedMaps> postedMaps) {
        this.userid = userid;
        this.profilepicture = profilepicture;
        this.username = username;
        this.password = password;
        this.primaryemail = primaryemail;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mini_bio = mini_bio;
        this.location = location;
        this.ulatitude = ulatitude;
        this.ulongitude = ulongitude;
        this.userroles = userroles;
        this.useremails = useremails;
        this.postedMaps = postedMaps;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(String profilepicture) {
        this.profilepicture = profilepicture;
    }

    public String getUsername() {
        if (username == null) // this is possible when updating a user
        {
            return null;
        } else
        {
            return username.toLowerCase();
        }
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    public String getPassword()
    {
        return password;
    }
    //TODO 24 Encrypt the pass
    //TODO AUTH 5 ENCRYPT THE PASSWORD
    public void setPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }
    //TODO AUTH 6 NON ENCODED
    public void setPasswordNotEncrypt(String password)
    {
        this.password = password;
    }
    public String getPrimaryemail() {
        if (primaryemail == null) // this is possible when updating a user
        {
            return null;
        } else
        {
            return primaryemail.toLowerCase();
        }
    }

    public void setPrimaryemail(String primaryemail) {
        this.primaryemail = primaryemail;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMini_bio() {
        return mini_bio;
    }

    public void setMini_bio(String mini_bio) {
        this.mini_bio = mini_bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getUlatitude() {
        return ulatitude;
    }

    public void setUlatitude(Double ulatitude) {
        this.ulatitude = ulatitude;
    }

    public Double getUlongitude() {
        return ulongitude;
    }

    public void setUlongitude(Double ulongitude) {
        this.ulongitude = ulongitude;
    }

    public List<UserRoles> getUserroles() {
        return userroles;
    }

    public void setUserroles(List<UserRoles> userroles) {
        this.userroles = userroles;
    }

    public List<Useremail> getUseremails() {
        return useremails;
    }

    public void setUseremails(List<Useremail> useremails) {
        this.useremails = useremails;
    }

    public List<PostedMaps> getPostedMaps() {
        return postedMaps;
    }

    public void setPostedMaps(List<PostedMaps> postedMaps) {
        this.postedMaps = postedMaps;
    }

    //TODO AUTH 4 SimpleGrantAuthority
    //TODO 23 Simple grant Auth granted to user
    @JsonIgnore
    public List<SimpleGrantedAuthority> getAuthority()
    {
        List<SimpleGrantedAuthority> rtnList = new ArrayList<>();

        for (UserRoles r : this.userroles)
        {
            String myRole = "ROLE_" + r.getRole()
                    .getName()
                    .toUpperCase();
            rtnList.add(new SimpleGrantedAuthority(myRole));
        }

        return rtnList;
    }
}
