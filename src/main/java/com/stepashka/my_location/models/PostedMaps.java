package com.stepashka.my_location.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.stepashka.my_location.logging.Loggable;

import javax.persistence.*;


@Loggable
@Entity
@Table(name = "postedmaps")
public class PostedMaps extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    @Column(nullable = true)
    private String address;
    @Column(nullable = true)
    private String map;
    @Column(nullable = true)
    private String city;
    @Column(nullable = true)
    private String state;
    @Column(nullable = true)
    private String zip;
    @Column(nullable = true)
    private String comments;
    @Column(nullable = true)
    private Float latitude;
    @Column(nullable = true)
    private Float longitude;
    @Column(nullable = true)
    private String created_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERID")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    public PostedMaps(){}

    public PostedMaps(long id, String title, String address, String map, String city, String state, String zip, String comments, Float latitude, Float longitude, String created_at, User user) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.map = map;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.comments = comments;
        this.latitude = latitude;
        this.longitude = longitude;
        this.created_at = created_at;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
