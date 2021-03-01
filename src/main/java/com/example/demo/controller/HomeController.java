package com.example.demo.controller;

import com.example.demo.model.Room;
import com.example.demo.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private RoomRepository roomRepository;

    @Autowired
    public HomeController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Room> all = roomRepository.findAll();
        model.addAttribute("rooms", all);
        return "index";
    }

}
