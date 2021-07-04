package com.example.demo.reservation;

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

    @PostMapping("/rooms/{id}/reservations") // s
    public ResponseEntity<?> createReservation(@PathVariable Long id, @Valid @RequestBody ReservationDto reservationDto) {
        System.out.println(reservationDto.toString());
        Optional<ReservationDto> reservationOptional = reservationService.create(reservationDto, id);
        if (reservationOptional.isPresent()) {
            return ResponseEntity.ok(reservationOptional.get());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }


    @PutMapping("/reservations/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<?> updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {

        Optional<ReservationDto> reservationDtoOptional = reservationService.updateReservation(id, reservation);
        if (reservationDtoOptional.isPresent()) {
            return ResponseEntity.ok(reservationDtoOptional.get());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/reservations/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<?> confirmPaymentOrDiscount(@PathVariable Long id, @RequestBody Map<String, Boolean> updates) {
        for (Map.Entry<String, Boolean> stringBooleanEntry : updates.entrySet()) {
            System.out.println(stringBooleanEntry.toString());
        }
                Optional<ReservationDto> reservationDtoOptional = reservationService.confirmPaymentOrDiscount(id, updates);
        if (reservationDtoOptional.isPresent()) {
            return ResponseEntity.ok(reservationDtoOptional.get());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        Optional<ReservationDto> reservationDtoOptional = reservationService.confirmPaymentOrDiscount(id);
//        if (reservationDtoOptional.isPresent()) {
//            return ResponseEntity.ok(reservationDtoOptional.get());
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping("/reservations/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<Map<String, Boolean>> deleteReservation(@PathVariable Long id) {
        Optional<ReservationDto> reservationDtoOptional = reservationService.deleteReservation(id);
        if (reservationDtoOptional.isPresent()) {
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}

