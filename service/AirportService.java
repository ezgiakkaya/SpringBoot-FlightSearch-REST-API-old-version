package com.example.flightsearchapi.service;

import com.example.flightsearchapi.model.Airport;
import com.example.flightsearchapi.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    public Airport createAirport(Airport airport) {
        // Save and return the newly created airport entity
        return airportRepository.save(airport);
    }

    public List<Airport> getAllAirports() {
        // Find and return all airports
        return airportRepository.findAll();
    }

    public Optional<Airport> getAirportById(String id) {
        // Find and return an airport by its id
        return airportRepository.findById(id);
    }

    public Optional<Airport> updateAirport(String id, Airport airportDetails) {
        // Update and return the updated airport entity
        return airportRepository.findById(id)
                .map(airport -> {
                    airport.setCity(airportDetails.getCity());
                    // Set other properties as needed
                    return airportRepository.save(airport);
                });
    }

    public boolean deleteAirport(String id) {
        // Delete an airport by its id if it exists
        return airportRepository.findById(id)
                .map(airport -> {
                    airportRepository.delete(airport);
                    return true;
                }).orElse(false);
    }

    public List<Airport> findAirportsByCity(String city) {
        return airportRepository.findByCity(city);
    }

}
