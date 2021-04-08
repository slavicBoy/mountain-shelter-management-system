package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.model.reservation.Reservation;
import com.example.demo.model.reservation.ReservationService;
import com.example.demo.repositories.ReservationRepository;
import com.example.demo.repositories.RoomRepository;
import com.example.demo.repositories.UnavailableTermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/")
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService, RoomRepository roomRepository, ReservationRepository reservationRepository, UnavailableTermRepository unavailableTermRepository) {
        this.reservationService = reservationService;
    }

    @PostMapping("/rooms/{id}/reservation")
    public void makeReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        Reservation save = reservationService.save(reservation, id);
        if (save == null) {
            System.out.println("niech lewica stąd sie zmyje na otwarcie parasola");
        }
    }



}

