//package com.example.demo.controller;
//
//import com.example.demo.model.room.Room;
//import com.example.demo.repositories.RoomRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:3000")
//@RequestMapping("/api/")
//public class HomeController {
//    private RoomRepository roomRepository;
//
//    @Autowired
//    public HomeController(RoomRepository roomRepository) {
//        this.roomRepository = roomRepository;
//    }
//
//    // get all rooms
//    @GetMapping("/rooms")
//    public List<Room> home() {
//        return roomRepository.findAll();
//    }
//
//}
