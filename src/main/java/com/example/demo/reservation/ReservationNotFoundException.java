package com.example.demo.reservation;

public class ReservationNotFoundException extends RuntimeException{

    public ReservationNotFoundException(String message) {
        super(message);
    }
}
