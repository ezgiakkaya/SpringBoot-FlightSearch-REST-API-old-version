package com.example.flightsearchapi.scheduled;

import com.example.flightsearchapi.repository.FlightRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.example.flightsearchapi.model.Flight;
import com.example.flightsearchapi.repository.FlightRepository;
import java.time.LocalDateTime;
import java.math.BigDecimal;

public class FlightDataFetcher {
    private final FlightRepository flightRepository;

    public FlightDataFetcher(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?") // This cron expression schedules the task to run at midnight every day
    public void fetchFlightData() {
        // Here you can simulate fetching data from a mock third-party API
        // For demonstration, we'll create some mock flight data and save it to the
        // database

        Flight mockFlight = new Flight("MockDepartureAirport", "MockArrivalAirport",
                LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2),
                new BigDecimal("200.00"));

        // Save the mock flight to the database
        flightRepository.save(mockFlight);

        // Log or indicate that the mock data has been fetched and saved
        System.out.println("Mock flight data fetched and saved to the database.");
    }
}
