package com.bezkoder.springjwt.controllers;

import java.util.Optional;

import com.bezkoder.springjwt.models.Flight;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.FlightRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    @Autowired
    private FlightRepository flightRepository;
  
    @Autowired
    private UserRepository userRepository;
    
    @PostMapping(path = "/addReservation") // Map ONLY POST Requests
    @CrossOrigin
  
    public @ResponseBody String newReservation(@RequestParam int userId, @RequestParam int flightId) {
      // @ResponseBody means the returned String is the response, not a view name
      // @RequestParam means it is a parameter from the GET or POST request
      Long uid = (long) userId;
      long fid = (long) flightId;
      Optional<Flight> flight = flightRepository.findById(fid);
      Optional<User> user = userRepository.findById(uid);
      if (user.isPresent() && flight.isPresent()) {
        System.out.println("added");
        User newuser = user.get();
        Flight newflight = flight.get();
        newuser.addReservation(newflight);
        userRepository.save(newuser);
      }
  
      return "Saved";
    }
     
  @PostMapping(path = "/removeReservation") 
  @CrossOrigin

  public @ResponseBody String removeReservation(@RequestParam int flightId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentUserName="";
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
    currentUserName = authentication.getName();
    }
    User user=userRepository.findByUsername(currentUserName).get();

    long fid = (long) flightId; 
    Flight flight = flightRepository.findById(fid).get();
    System.out.println(user.getReservations().size());
    if (user!=null && flight!=null) {      
      user.removeReservation(flight);
      userRepository.save(user);
    }
  System.out.println(user.getReservations().size());
    return "Cancel";
  }

  @PostMapping(path = "/getUserReservations") 
  @CrossOrigin

  public @ResponseBody User getUserReservations() throws JsonProcessingException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentUserName="";
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
    currentUserName = authentication.getName();
    }
    User user=userRepository.findByUsername(currentUserName).get();

    

      return user;

    
  }
}
