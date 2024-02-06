package com.example.flightsearchapi.repository;

import com.example.flightsearchapi.model.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends MongoRepository<Flight, String> {
    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureTimeBetween(
            String departureAirport, String arrivalAirport, LocalDateTime departureStart, LocalDateTime departureEnd);

    // if search is based on city names
    /*
     * List<Flight>
     * findByDepartureAirportInAndArrivalAirportInAndDepartureTimeBetween(
     * List<String> departureAirportIds, List<String> arrivalAirportIds,
     * LocalDateTime start, LocalDateTime end);
     * 
     */
}
