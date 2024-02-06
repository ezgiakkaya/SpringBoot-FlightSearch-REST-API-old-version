package com.example.flightsearchapi.service;

import com.example.flightsearchapi.model.Airport;
import com.example.flightsearchapi.model.Flight;
import com.example.flightsearchapi.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> searchFlights(String departure, String arrival, LocalDateTime departureStart,
            LocalDateTime departureEnd) {
        return flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureTimeBetween(departure, arrival,
                departureStart, departureEnd);
    }

    public Optional<Flight> findById(String id) {
        return flightRepository.findById(id);
    }

    public Optional<Flight> updateFlight(String id, Flight flightDetails) {
        return flightRepository.findById(id)
                .map(existingFlight -> {
                    existingFlight.setDepartureAirport(flightDetails.getDepartureAirport());
                    existingFlight.setArrivalAirport(flightDetails.getArrivalAirport());
                    existingFlight.setDepartureTime(flightDetails.getDepartureTime());
                    existingFlight.setReturnTime(flightDetails.getReturnTime());
                    existingFlight.setPrice(flightDetails.getPrice());
                    return flightRepository.save(existingFlight);
                });
    }

    public void deleteFlight(String id) {
        flightRepository.deleteById(id);
    }

    public List<Flight> getAllFlights() {
        // Find and return all airports
        return flightRepository.findAll();
    }

    public List<List<Flight>> findFlights(String departure, String arrival, LocalDateTime departureDate,
            LocalDateTime returnDate) {
        List<Flight> outboundFlights = flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureTimeBetween(
                departure, arrival, departureDate, departureDate.plusDays(1)); // Assuming full-day search

        List<List<Flight>> result = new ArrayList<>();
        result.add(outboundFlights); // Always include outbound flights

        if (returnDate != null) {
            List<Flight> returnFlights = flightRepository
                    .findByDepartureAirportAndArrivalAirportAndDepartureTimeBetween(
                            arrival, departure, returnDate, returnDate.plusDays(1)); // Assuming full-day search
            result.add(returnFlights); // Include return flights if return date is provided
        }

        return result;
    }

    // if search is by city instead of airport
    /*
     * 
     * private AirportService airportService;
     * 
     * public List<List<Flight>> findFlights(String departureCity, String
     * arrivalCity, LocalDateTime departureDate, Optional<LocalDateTime>
     * returnDateOpt) {
     * List<Airport> departureAirports =
     * airportService.findAirportsByCity(departureCity);
     * List<Airport> arrivalAirports =
     * airportService.findAirportsByCity(arrivalCity);
     * 
     * List<String> departureAirportIds =
     * departureAirports.stream().map(Airport::getId).collect(Collectors.toList());
     * List<String> arrivalAirportIds =
     * arrivalAirports.stream().map(Airport::getId).collect(Collectors.toList());
     * 
     * List<Flight> outboundFlights =
     * flightRepository.findFlightsByAirportIdsAndDateRange(departureAirportIds,
     * arrivalAirportIds, departureDate);
     * 
     * List<List<Flight>> results = new ArrayList<>();
     * results.add(outboundFlights);
     * 
     * returnDateOpt.ifPresent(returnDate -> {
     * List<Flight> returnFlights =
     * flightRepository.findFlightsByAirportIdsAndDateRange(arrivalAirportIds,
     * departureAirportIds, returnDate);
     * results.add(returnFlights);
     * });
     * 
     * return results;
     * }
     * 
     */
}
