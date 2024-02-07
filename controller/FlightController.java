package com.example.flightsearchapi.controller;

import com.example.flightsearchapi.model.Flight;
import com.example.flightsearchapi.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // Search flights based on departure, arrival, and optional departure time range
    @GetMapping("/search")
    public List<Flight> searchFlights(
            @RequestParam String departure,
            @RequestParam String arrival,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureStart,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureEnd) {

        return flightService.searchFlights(departure, arrival, departureStart, departureEnd);
    }

    // Update a flight's details by ID
    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable String id, @RequestBody Flight flightDetails) {
        Optional<Flight> updatedFlight = flightService.updateFlight(id, flightDetails);

        return updatedFlight
                .map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a flight by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteFlight(@PathVariable("id") String id) {
        try {
            flightService.deleteFlight(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Search flights with complex criteria (departure, arrival, and optional dates
    // for departure and return)
    @GetMapping("/advanced-search")
    public ResponseEntity<List<List<Flight>>> advancedSearchFlights(
            @RequestParam String departure,
            @RequestParam String arrival,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> returnDate) {

        System.out.println("new request : " + departure + " " + arrival + " " + departureDate);
        List<List<Flight>> flights = flightService.findFlights(departure, arrival, departureDate,
                returnDate.orElse(null));
        return ResponseEntity.ok(flights);
    }

    // Search flights with complex criteria (departure, arrival, and optional dates
    // for departure and return)
    @GetMapping("/more-search")
    public ResponseEntity<List<List<Flight>>> moreAdvancedSearchFlights(
            @RequestParam String departure,
            @RequestParam String arrival,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> returnDate) {

        System.out.println("new request : " + departure + " " + arrival + " " + departureDate);
        // Pass returnDate directly as an Optional
        List<List<Flight>> flights = flightService.findAdvancedFlights(departure, arrival, departureDate, returnDate);
        return ResponseEntity.ok(flights);
    }

    // Get details of a flight by ID
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable("id") String id) {
        Optional<Flight> flight = flightService.findById(id);
        return flight
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all flights (Fixed mapping to prevent conflict)
    @GetMapping("/all")
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

}
