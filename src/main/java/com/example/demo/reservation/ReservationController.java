package com.example.demo.reservation;

import com.example.demo.reservation.exception.ReservationNotFoundException;
import com.example.demo.reservation.unavailableTerm.DateUnavailableException;
import com.example.demo.room.Room;
import com.example.demo.room.RoomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    @PreAuthorize("hasRole('WORKER') or hasRole('OWNER')")
    public List<ReservationDto> findAll() {
        return reservationService.findAll();

    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<?> getReservationById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reservationService.findById(id));
        } catch (RoomNotFoundException | DateUnavailableException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    public ResponseEntity<?> createReservation(@PathVariable Long id, @Valid @RequestBody ReservationDto reservationDto) {
        try {
            return ResponseEntity.ok(reservationService.create(reservationDto, id));
        } catch (RoomNotFoundException | DateUnavailableException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<?> updateReservation(@PathVariable Long id, @RequestBody ReservationDto reservationDto) {
        try {
            return ResponseEntity.ok(reservationService.updateReservation(id, reservationDto));
        } catch (ReservationNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<?> confirmPaymentOrDiscount(@PathVariable Long id, @RequestBody Map<String, Boolean> updates) {
        for (Map.Entry<String, Boolean> stringBooleanEntry : updates.entrySet()) {
            System.out.println(stringBooleanEntry);
        }

        try {
            return ResponseEntity.ok(reservationService.confirmPaymentOrDiscount(id, updates));
        } catch (ReservationNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reservationService.deleteReservation(id));
        } catch (ReservationNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }


}

