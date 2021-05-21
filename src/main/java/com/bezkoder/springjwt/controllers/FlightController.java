package com.bezkoder.springjwt.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bezkoder.springjwt.models.Flight;
import com.bezkoder.springjwt.repository.FlightRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/flight")
public class FlightController {
    @Autowired
    private FlightRepository flightRepository;

  @PostMapping(path = "/addFlight") // Map ONLY POST Requests

  public @ResponseBody String newFlight(@RequestParam String departing, @RequestParam String destination,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date departureTime) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request
    Flight n = new Flight();
    n.setDeparting(departing);
    n.setDestination(destination);
    n.setDepartureTime(departureTime);
    flightRepository.save(n);
    return "Saved";
  }
  
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Flight> getAllUsers() {
        
    // This returns a JSON or XML with the users
      return flightRepository.findAll();
    } 
    @GetMapping(path="/search")
  @CrossOrigin

  public @ResponseBody Iterable<Flight> searchFlights(@RequestParam String departing
  , @RequestParam String destination, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date departureTime) {
    // This returns a JSON or XML with the users
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh");  
    String strDate = dateFormat.format(departureTime);  

    List<Flight> flights=flightRepository.findFlights(departing, destination, strDate);
    return flights;
}




}

