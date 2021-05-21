package com.bezkoder.springjwt.models;


import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private long flight_id;
    @Column(name = "departing")

    private String departing;
    @Column(name = "destination")

    private String destination;
    @Column(name = "departure_time")
    private Date departureTime;
    @ManyToMany(mappedBy = "reservations")
    @JsonIgnore
    Set<User> bookings;
    public Flight(){

    }
    Flight(String departing, String destination,Date departureTime){
        this.departing= departing;
        this.destination=destination;
        this.departureTime=departureTime;
        
    }

    public long getId() {
        return flight_id;
    }

    public void setId(long id) {
        this.flight_id = id;
    }

    public String getDeparting() {
        return departing;
    }

    public void setDeparting(String departing) {
        this.departing = departing;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Set<User> getBookings() {
        return bookings;
    }

    public void setBookings(Set<User> bookings) {
        this.bookings = bookings;
    }
    public void addBooking(User user) {
        this.bookings.add(user);
    }



}
