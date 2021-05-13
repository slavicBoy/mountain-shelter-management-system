package com.example.demo.unavailableTerm;

import com.example.demo.reservation.Reservation;
import com.example.demo.room.Room;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "unavailable_term")
public class UnavailableTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_of_unavailable_term")
    private LocalDate startOfUnavailableTerm;
    @Column(name = "end_of_unavailable_term")
    private LocalDate endOfUnavailableTerm;
    @Column(name = "places_available")
    private Integer placesAvailable;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    @OneToOne(mappedBy = "unavailableTerm")
    private Reservation reservation;



    public UnavailableTerm() {
    }

    public UnavailableTerm(LocalDate startOfUnavailableTerm, LocalDate endOfUnavailableTerm) {
        this.startOfUnavailableTerm = startOfUnavailableTerm;
        this.endOfUnavailableTerm = endOfUnavailableTerm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartOfUnavailableTerm() {
        return startOfUnavailableTerm;
    }

    public void setStartOfUnavailableTerm(LocalDate startOfUnavailableTerm) {
        this.startOfUnavailableTerm = startOfUnavailableTerm;
    }

    public LocalDate getEndOfUnavailableTerm() {
        return endOfUnavailableTerm;
    }

    public void setEndOfUnavailableTerm(LocalDate endOfUnavailableTerm) {
        this.endOfUnavailableTerm = endOfUnavailableTerm;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Integer getPlacesAvailable() {
        return placesAvailable;
    }

    public void setPlacesAvailable(Integer placesAvailable) {
        this.placesAvailable = placesAvailable;
    }
}
