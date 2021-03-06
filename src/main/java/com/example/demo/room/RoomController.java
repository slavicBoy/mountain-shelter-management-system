package com.example.demo.room;


import com.example.demo.reservation.Reservation;
import com.example.demo.reservation.ReservationController;
import com.example.demo.reservation.ReservationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/rooms")
public class RoomController {

    private RoomService roomService;
    private ReservationController reservationController;

    @Autowired
    public RoomController(RoomService roomService, ReservationController reservationController) {
        this.roomService = roomService;
        this.reservationController = reservationController;
    }

    @PostMapping
    public List<RoomDto> findAvailableRooms(@RequestBody ArrivalDetailsDto arrivalDetails) {
        return roomService.findAvailableRooms(arrivalDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(roomService.findById(id));
        } catch (RoomNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/reservations")
    public ResponseEntity<?> createReservation(@PathVariable Long id, @Valid @RequestBody ReservationDto reservationDto) {
       return reservationController.createReservation(id, reservationDto);
    }

    @GetMapping
    public List<RoomDto> findAll() {
        return roomService.findAll();
    }


}
