package com.example.demo.reservation;

import com.example.demo.room.Room;
import com.example.demo.unavailableTerm.UnavailableTerm;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 20)
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name", length = 30)
    private String lastName;
    @Column(name = "how_many_people")
    private int howManyPeople;
    @Column(name = "phone_number", length = 12)
    private String phoneNumber;
    @Column(name = "reservation_start")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationDayStart;
    @Column(name = "reservation_end")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationDayEnd;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_details")
    private ReservationDetails details;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "unavailable_term_id")
    private UnavailableTerm unavailableTerm;

    public Reservation() {

    }

    public Reservation(String firstName, String lastName, int howManyPeople, String phoneNumber, LocalDate reservationDayStart, LocalDate reservationDayEnd) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.howManyPeople = howManyPeople;
        this.phoneNumber = phoneNumber;
        this.reservationDayStart = reservationDayStart;
        this.reservationDayEnd = reservationDayEnd;
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

    public int getHowManyPeople() {
        return howManyPeople;
    }

    public void setHowManyPeople(int howManyPeople) {
        this.howManyPeople = howManyPeople;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public ReservationDetails getDetails() {
        return details;
    }

    public void setDetails(ReservationDetails details) {
        this.details = details;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public UnavailableTerm getUnavailableTerm() {
        return unavailableTerm;
    }

    public void setUnavailableTerm(UnavailableTerm unavailableTerm) {
        this.unavailableTerm = unavailableTerm;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", howManyPeople=" + howManyPeople +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", reservationDayStart=" + reservationDayStart +
                ", reservationDayEnd=" + reservationDayEnd +
                ", details=" + details +
                ", room=" + room +
                '}';
    }


}

