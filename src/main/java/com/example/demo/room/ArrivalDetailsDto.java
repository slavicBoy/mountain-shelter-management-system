package com.example.demo.room;

import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

public class ArrivalDetailsDto {
    private LocalDate reservationDayStart;
    private LocalDate reservationDayEnd;
    private int numberOfPeople;

    public ArrivalDetailsDto(LocalDate reservationDayStart, LocalDate reservationDayEnd, int numberOfPeople) {
        this.reservationDayStart = reservationDayStart;
        this.reservationDayEnd = reservationDayEnd;
        this.numberOfPeople = numberOfPeople;
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

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }


    @Override
    public String toString() {
        return "ArrivalDetails{" +
                "reservationDayStart=" + reservationDayStart +
                ", reservationDayEnd=" + reservationDayEnd +
                ", numberOfPeople=" + numberOfPeople +
                '}';
    }
}
