package com.example.demo.room;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/")
public class RoomController {

    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/rooms")
    public List<RoomDto> findAvailableRooms(@RequestBody ArrivalDetailsDto arrivalDetails) {
        return roomService.findAvailableRooms(arrivalDetails);
    }

/*    @GetMapping("/rooms/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<RoomDto> roomDtoOptional = roomService.findById(id);
        if (roomDtoOptional.isPresent()) {
            return ResponseEntity.ok(roomDtoOptional.get());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }*/

    @GetMapping("/rooms/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(roomService.findById(id));
        } catch (RoomNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/rooms")
    public List<RoomDto> findAll() {
        return roomService.findAll();
    }


}
