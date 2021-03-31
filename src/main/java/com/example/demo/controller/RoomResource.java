package com.example.demo.controller;

import com.example.demo.model.reservation.Reservation;
import com.example.demo.model.room.RoomDto;
import com.example.demo.model.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/")
public class RoomResource {

    private RoomService roomService;

    @Autowired
    public RoomResource(RoomService roomService) {
        this.roomService = roomService;
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
    public Reservation makeReservation(@PathVariable Long id, @RequestBody Reservation reservation){
        System.out.println(reservation.toString());
        System.out.println(id);
        return null;
    }
}
