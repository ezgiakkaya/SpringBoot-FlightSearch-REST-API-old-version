package com.example.flightsearchapi.service;

import com.example.flightsearchapi.model.Airport;
import com.example.flightsearchapi.model.Flight;
import com.example.flightsearchapi.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
        List<List<Flight>> result = new ArrayList<>();

        System.out.println(departure + " " + arrival + " " + departureDate + " " + returnDate);
        List<Flight> outboundFlights = flightRepository
                .findByDepartureAirportAndArrivalAirportAndDepartureTimeBetween(
                        departure, arrival, departureDate, returnDate); // Assuming full-day search
        result.add(outboundFlights); // Always include outbound flights
        return result;
    }

    public List<List<Flight>> findAdvancedFlights(String departure, String arrival, LocalDateTime departureDate,
            Optional<LocalDateTime> returnDateOpt) {
        List<List<Flight>> result = new ArrayList<>();

        // Calculate start and end of the departure day
        LocalDateTime startOfDepartureDay = departureDate.toLocalDate().atStartOfDay();
        LocalDateTime endOfDepartureDay = departureDate.toLocalDate().atTime(23, 59, 59);

        // Find outbound flights departing on the specified departure date
        List<Flight> outboundFlights = flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureTimeBetween(
                departure, arrival, startOfDepartureDay, endOfDepartureDay);
        result.add(outboundFlights);

        // If a return date is provided, find return flights on the return date
        returnDateOpt.ifPresent(returnDate -> {
            // Calculate start and end of the return day
            LocalDateTime startOfReturnDay = returnDate.toLocalDate().atStartOfDay();
            LocalDateTime endOfReturnDay = returnDate.toLocalDate().atTime(23, 59, 59);

            // Find return flights departing from 'arrival' to 'departure' on the return
            // date
            List<Flight> returnFlights = flightRepository
                    .findByDepartureAirportAndArrivalAirportAndDepartureTimeBetween(
                            arrival, departure, startOfReturnDay, endOfReturnDay);

            // Optionally, to ensure clear separation, you may want to keep return flights
            // in a separate list
            // This depends on how you want to structure the data (e.g., one list for
            // outbound and one for return flights)
            result.add(returnFlights);
        });

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
