package com.example.flightsearchapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "airports")
public class Airport {
    @Id
    private String id;
    private String city;

    // Constructors
    public Airport() {
    }

    public Airport(String city) {
        this.city = city;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // toString, equals, and hashCode methods
}
