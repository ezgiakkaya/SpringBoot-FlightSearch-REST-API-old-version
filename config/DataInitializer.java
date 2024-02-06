package com.example.flightsearchapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.flightsearchapi.model.Airport;
import com.example.flightsearchapi.model.Flight;
import com.example.flightsearchapi.repository.AirportRepository;
import com.example.flightsearchapi.repository.FlightRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists to prevent duplicate initialization
        if (airportRepository.count() == 0) {
            // Initialize airports data
            Airport airport1 = new Airport("New York JFK");
            Airport airport2 = new Airport("Los Angeles LAX");
            airportRepository.save(airport1);
            airportRepository.save(airport2);
            // Add more airports as needed
        }

        if (flightRepository.count() == 0) {
            // Initialize flights data
            Flight flight = new Flight("New York JFK", "Los Angeles LAX", LocalDateTime.now(),
                    LocalDateTime.now().plusHours(5), new BigDecimal("199.99"));
            flightRepository.save(flight);
            // Add more flights as needed
        }
    }
}
