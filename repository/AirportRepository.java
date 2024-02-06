package com.example.flightsearchapi.repository;

import com.example.flightsearchapi.model.Airport;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends MongoRepository<Airport, String> {
    // custom query methods
    List<Airport> findByCity(String city);

}