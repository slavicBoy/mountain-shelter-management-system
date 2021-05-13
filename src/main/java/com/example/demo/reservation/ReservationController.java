package com.example.demo.reservation;

import com.example.demo.exception.DateUnavailableException;
import com.example.demo.exception.RoomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/")
public class ReservationController {

    private ReservationService reservationService;


    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations")
    @PreAuthorize("hasRole('WORKER') or hasRole('OWNER')")
    public List<ReservationDto> findAll() {
        return reservationService.findAll();

    }

    @GetMapping("/reservations/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public ReservationDto getReservationById(@PathVariable Long id) {
        return reservationService.findById(id);
    }

    @PostMapping("/rooms/{id}/reservation")
    public ResponseEntity<?> createReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        try {
            Optional<Reservation> reservationOptional = reservationService.create(reservation, id);
            return ResponseEntity.ok(reservationOptional);
        } catch (DateUnavailableException | RoomNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/reservations/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public ReservationDto updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        return reservationService.updateReservation(id, reservation);
    }

    @DeleteMapping("/reservations/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }


}

