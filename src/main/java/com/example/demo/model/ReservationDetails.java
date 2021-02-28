package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservation_details")
public class ReservationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "how_many_people", nullable = false)
    private Integer howManyPeople;
    @Column(name = "resevation_start", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationDayStart;
    @Column(name = "resevation_end", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationDayEnd;
    @Column(name = "date_of_adding_reservation", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfAddingReservation;
    @Column(name = "extra_information", nullable = true)
    private String extraInformation;
    @OneToOne(mappedBy = "details")
    private Reservation reservation;

    public ReservationDetails() {
    }

    public ReservationDetails(Integer howManyPeople, LocalDate reservationDayStart, LocalDate reservationDayEnd, LocalDate dateOfAddingReservation, String extraInformation) {
        this.howManyPeople = howManyPeople;
        this.reservationDayStart = reservationDayStart;
        this.reservationDayEnd = reservationDayEnd;
        this.dateOfAddingReservation = dateOfAddingReservation;
        this.extraInformation = extraInformation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHowManyPeople() {
        return howManyPeople;
    }

    public void setHowManyPeople(Integer howManyPeople) {
        this.howManyPeople = howManyPeople;
    }

    public LocalDate getReservationDayStart() {
        return reservationDayStart;
    }

    public void setReservationDayStart(LocalDate reservationDayStart) {
        this.reservationDayStart = reservationDayStart;
    }

    public LocalDate getReservationDayEnd() {
        return reservationDayEnd;
    }

    public void setReservationDayEnd(LocalDate reservationDayEnd) {
        this.reservationDayEnd = reservationDayEnd;
    }

    public LocalDate getDateOfAddingReservation() {
        return dateOfAddingReservation;
    }

    public void setDateOfAddingReservation(LocalDate dateOfAddingReservation) {
        this.dateOfAddingReservation = dateOfAddingReservation;
    }

    public String getExtraInformation() {
        return extraInformation;
    }

    public void setExtraInformation(String extraInformation) {
        this.extraInformation = extraInformation;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
