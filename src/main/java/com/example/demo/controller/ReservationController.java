package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.model.reservation.Reservation;
import com.example.demo.model.reservation.ReservationDto;
import com.example.demo.model.reservation.ReservationService;
import com.example.demo.repositories.ReservationRepository;
import com.example.demo.repositories.RoomRepository;
import com.example.demo.repositories.UnavailableTermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/")
public class ReservationController {

    private ReservationService reservationService;


    @Autowired
    public ReservationController(ReservationService reservationService, RoomRepository roomRepository, ReservationRepository reservationRepository, UnavailableTermRepository unavailableTermRepository) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations")
    public List<ReservationDto> findAll() {
        return reservationService.findAll();
    }

    @GetMapping("/reservations/{id}")
    public ReservationDto getReservationById(@PathVariable Long id) {
        return reservationService.findById(id);
    }

    @PostMapping("/rooms/{id}/reservation")
    public void createReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        Reservation save = reservationService.create(reservation, id);
        if (save == null) {
            System.out.println("Pokój niedostępny");
        }
    }

    @PutMapping("/reservations/{id}")
    public ReservationDto updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        System.out.println("dupa");
        return reservationService.updateReservation(id, reservation);
    }

    @DeleteMapping("/reservations/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }


}

