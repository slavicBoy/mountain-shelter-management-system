package com.example.demo.room;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/")
public class RoomController {

    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms")
    public List<RoomDto> findAll() {
        return roomService.findAll();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<RoomDto> roomDtoOptional = roomService.findById(id);
        if (roomDtoOptional.isPresent()) {
            return ResponseEntity.ok(roomDtoOptional.get());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
