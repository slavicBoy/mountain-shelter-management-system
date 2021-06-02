package com.example.demo.reservation;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReservationDto {

    private Long id;
    private int howManyPeople;
    private String firstName;
    private String LastName;
    private String phoneNumber;
    private LocalDate reservationStart;
    private LocalDate reservationEnd;
    private String email;
    private String extraInformation;
    private LocalDate reservationTime;
    private BigDecimal amountToPay;
    private boolean wasDiscountShowed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getHowManyPeople() {
        return howManyPeople;
    }

    public void setHowManyPeople(int howManyPeople) {
        this.howManyPeople = howManyPeople;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getReservationStart() {
        return reservationStart;
    }

    public void setReservationStart(LocalDate reservationStart) {
        this.reservationStart = reservationStart;
    }

    public LocalDate getReservationEnd() {
        return reservationEnd;
    }

    public void setReservationEnd(LocalDate reservationEnd) {
        this.reservationEnd = reservationEnd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExtraInformation() {
        return extraInformation;
    }

    public void setExtraInformation(String extraInformation) {
        this.extraInformation = extraInformation;
    }

    public LocalDate getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDate reservationTime) {
        this.reservationTime = reservationTime;
    }


    public BigDecimal getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(BigDecimal amountToPay) {
        this.amountToPay = amountToPay;
    }

    public boolean isWasDiscountShowed() {
        return wasDiscountShowed;
    }

    public void setWasDiscountShowed(boolean wasDiscountShowed) {
        this.wasDiscountShowed = wasDiscountShowed;
    }
}
