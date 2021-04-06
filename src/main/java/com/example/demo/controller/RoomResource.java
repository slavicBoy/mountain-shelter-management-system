package com.example.demo.controller;

import com.example.demo.model.reservation.Reservation;
import com.example.demo.model.reservation.ReservationService;
import com.example.demo.model.room.RoomDto;
import com.example.demo.model.room.RoomMapper;
import com.example.demo.model.room.RoomService;
import com.example.demo.repositories.ReservationRepository;
import com.example.demo.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/")
public class RoomResource {

    private RoomService roomService;
    private ReservationService reservationService;

    @Autowired
    public RoomResource(RoomService roomService, ReservationService reservationService) {
        this.roomService = roomService;
        this.reservationService = reservationService;
    }

    @GetMapping("/rooms")
    public List<RoomDto> findAll() {
        return roomService.findAll();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<RoomDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.findById(id));
    }

    @PostMapping("/rooms/{id}/reservation")
    public void makeReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        Reservation save = reservationService.save(reservation, id);
        if (save == null) {
            System.out.println("niech lewica stąd sie zmyje na otwarcie parasola");
        }
    }
}
