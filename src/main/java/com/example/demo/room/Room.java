package com.example.demo.room;

import com.example.demo.reservation.Reservation;
import com.example.demo.reservation.unavailableTerm.UnavailableTerm;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description", nullable = true, length = 200)
    private String description;
    @Column(name = "for_how_many_people")
    private Integer forHowManyPeople;
    @Column(name = "bathroom")
    private boolean isBathroom;
    @Column(name = "price_by_person")
    private BigDecimal pricePerPeron;
    @Column(name = "img_url")
    private String imgUrl;
    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;
    @OneToMany(mappedBy = "room")
    private List<UnavailableTerm> unavailableTerms;

    // OBIEKT STRATEGIA WYLICZANIA WOLNYCH MIEJSC

    {
        reservations = new ArrayList<>();
        unavailableTerms = new ArrayList<>();
    }


    public Room() {
    }

    public Room(String description, Integer forHowManyPeople, boolean isBathroom, BigDecimal pricePerPeron, String imgUrl) {
        this.description = description;
        this.forHowManyPeople = forHowManyPeople;
        this.isBathroom = isBathroom;
        this.pricePerPeron = pricePerPeron;
        this.imgUrl = imgUrl;
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

    public List<UnavailableTerm> getUnavailableTerms() {
        return unavailableTerms;
    }



    public void setUnavailableTerms(List<UnavailableTerm> unavailableTerms) {
        this.unavailableTerms = unavailableTerms;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
