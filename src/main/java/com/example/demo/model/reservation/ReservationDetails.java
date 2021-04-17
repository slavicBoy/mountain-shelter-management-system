package com.example.demo.model.reservation;

import com.example.demo.model.worker.Worker;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservation_details")
public class ReservationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_of_adding_reservation")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfAddingReservation;
    @Column(name = "extra_information", nullable = true)
    private String extraInformation;
    @Column(name = "email")
    private String email;
    @OneToOne(mappedBy = "details")
    private Reservation reservation;
    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;


    public ReservationDetails() {
    }

    public ReservationDetails(Integer howManyPeople, LocalDate dateOfAddingReservation, String extraInformation, Reservation reservation) {
        this.dateOfAddingReservation = dateOfAddingReservation;
        this.extraInformation = extraInformation;
        this.reservation = reservation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "ReservationDetails{" +
                ", dateOfAddingReservation=" + dateOfAddingReservation +
                ", extraInformation='" + extraInformation + '\'' +
                ", email='" + email + '\'' +

                '}';
    }
}
