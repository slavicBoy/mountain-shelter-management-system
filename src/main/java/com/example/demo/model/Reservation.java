package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;
    @Column(name = "last_name", length = 30, nullable = false)
    private String lastName;
    @Column(name = "phone_number", length = 12, nullable = false)
    private String phoneNumber;
    @Column(name = "email", nullable = false)
    private String email;
    @OneToOne
    @JoinColumn(name = "id_details")
    private ReservationDetails details;
    @ManyToMany
    @JoinTable(name = "reservations", joinColumns = {@JoinColumn(name = "reservation_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "room_id", referencedColumnName = "id")})
    private List<Room> rooms;

    public Reservation() {
    }

    public Reservation(String firstName, String lastName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ReservationDetails getReservationDetails() {
        return details;
    }

    public void setReservationDetails(ReservationDetails reservationDetails) {
        this.details = reservationDetails;
    }

    public ReservationDetails getDetails() {
        return details;
    }

    public void setDetails(ReservationDetails details) {
        this.details = details;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
    public void addRoom(Room room) {
        if (rooms == null) {
            rooms = new ArrayList<>();
        }
        rooms.add(room);
    }

}
