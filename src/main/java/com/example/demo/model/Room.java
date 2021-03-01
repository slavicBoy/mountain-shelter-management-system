package com.example.demo.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description", nullable = true, length = 200)
    private String description;
    @Column(name = "for_how_many_people", nullable = false)
    private Integer forHowManyPeople;
    @Column(name = "bathroom", nullable = false)
    private boolean isBathroom;
    @Column(name = "price_by_person", nullable = false)
    private BigDecimal pricePerPeron;
    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;
    @OneToMany(mappedBy = "room")
    private List<UnavailableTerm> unavailableTerms;

    public Room() {
    }

    public Room(String description, Integer forHowManyPeople, boolean isBathroom, BigDecimal pricePerPeron) {
        this.description = description;
        this.forHowManyPeople = forHowManyPeople;
        this.isBathroom = isBathroom;
        this.pricePerPeron = pricePerPeron;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getForHowManyPeople() {
        return forHowManyPeople;
    }

    public void setForHowManyPeople(Integer forHowManyPeople) {
        this.forHowManyPeople = forHowManyPeople;
    }

    public boolean isBathroom() {
        return isBathroom;
    }

    public void setBathroom(boolean bathroom) {
        isBathroom = bathroom;
    }

    public BigDecimal getPricePerPeron() {
        return pricePerPeron;
    }

    public void setPricePerPeron(BigDecimal pricePerPeron) {
        this.pricePerPeron = pricePerPeron;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void addReservation(Reservation reservation) {
        if (reservations == null) {
            reservations = new ArrayList<>();
        }
        reservations.add(reservation);
    }
    public void addUnavailableTerm(UnavailableTerm unavailableTerm) {
        if (unavailableTerms == null) {
            unavailableTerms = new ArrayList<>();
        }
        unavailableTerms.add(unavailableTerm);
    }
}
