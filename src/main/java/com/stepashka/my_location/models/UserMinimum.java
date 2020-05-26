package com.stepashka.my_location.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stepashka.my_location.logging.Loggable;

import javax.persistence.Column;
import javax.validation.constraints.Email;
@Loggable
public class UserMinimum {
    private String profilepicture;
    private String username;
    private String password;
    private String primaryemail;
    private String firstname;
    private String lastname;
    private String mini_bio;
    private String location;


    public String getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(String profilepicture) {
        this.profilepicture = profilepicture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrimaryemail() {
        return primaryemail;
    }

    public void setPrimaryemail(String primaryemail) {
        this.primaryemail = primaryemail;
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

}
