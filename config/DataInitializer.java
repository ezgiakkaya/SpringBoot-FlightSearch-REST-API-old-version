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
            // Add more airports as needed
            Airport airport3 = new Airport("Chicago ORD");
            Airport airport4 = new Airport("San Francisco SFO");
            Airport airport5 = new Airport("Miami MIA");
            Airport airport6 = new Airport("Dallas DFW");
            Airport airport7 = new Airport("Denver DEN");
            Airport airport8 = new Airport("Las Vegas LAS");
            Airport airport9 = new Airport("Seattle SEA");
            Airport airport10 = new Airport("Atlanta ATL");

            airportRepository.save(airport1);
            airportRepository.save(airport2);
            airportRepository.save(airport3);
            airportRepository.save(airport4);
            airportRepository.save(airport5);
            airportRepository.save(airport6);
            airportRepository.save(airport7);
            airportRepository.save(airport8);
            airportRepository.save(airport9);
            airportRepository.save(airport10);
        }

        if (flightRepository.count() == 0) {
            // Manually create and save at least 10 different flights between different
            // airports
            Flight flight1 = new Flight("New York JFK", "Los Angeles LAX", LocalDateTime.now(),
                    LocalDateTime.now().plusHours(5), new BigDecimal("199.99"));
            Flight flight2 = new Flight("Los Angeles LAX", "New York JFK", LocalDateTime.now().plusDays(1),
                    LocalDateTime.now().plusDays(1).plusHours(5), new BigDecimal("199.99"));
            Flight flight3 = new Flight("Chicago ORD", "San Francisco SFO", LocalDateTime.now().plusDays(2),
                    LocalDateTime.now().plusDays(2).plusHours(5), new BigDecimal("249.99"));
            Flight flight4 = new Flight("Miami MIA", "Dallas DFW", LocalDateTime.now().plusDays(3),
                    LocalDateTime.now().plusDays(3).plusHours(4), new BigDecimal("189.99"));
            Flight flight5 = new Flight("Denver DEN", "Las Vegas LAS", LocalDateTime.now().plusDays(4),
                    LocalDateTime.now().plusDays(4).plusHours(3), new BigDecimal("149.99"));
            Flight flight6 = new Flight("Atlanta ATL", "New York JFK", LocalDateTime.now().plusDays(5),
                    LocalDateTime.now().plusDays(5).plusHours(5), new BigDecimal("179.99"));
            Flight flight7 = new Flight("San Francisco SFO", "Chicago ORD", LocalDateTime.now().plusDays(6),
                    LocalDateTime.now().plusDays(6).plusHours(6), new BigDecimal("259.99"));
            Flight flight8 = new Flight("Seattle SEA", "Denver DEN", LocalDateTime.now().plusDays(7),
                    LocalDateTime.now().plusDays(7).plusHours(4), new BigDecimal("219.99"));
            Flight flight9 = new Flight("Dallas DFW", "Miami MIA", LocalDateTime.now().plusDays(8),
                    LocalDateTime.now().plusDays(8).plusHours(3), new BigDecimal("189.99"));
            Flight flight10 = new Flight("Los Angeles LAX", "Atlanta ATL", LocalDateTime.now().plusDays(9),
                    LocalDateTime.now().plusDays(9).plusHours(5), new BigDecimal("219.99"));

            flightRepository.save(flight1);
            flightRepository.save(flight2);
            flightRepository.save(flight3);
            flightRepository.save(flight4);
            flightRepository.save(flight5);
            flightRepository.save(flight6);
            flightRepository.save(flight7);
            flightRepository.save(flight8);
            flightRepository.save(flight9);
            flightRepository.save(flight10);
        }
    }
}
