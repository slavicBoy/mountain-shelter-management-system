package com.example.demo.controller;

import com.example.demo.model.Reservation;
import com.example.demo.model.ReservationDetails;
import com.example.demo.model.Room;
import com.example.demo.model.UnavailableTerm;
import com.example.demo.repositories.ReservationDetailsRepository;
import com.example.demo.repositories.ReservationRepository;
import com.example.demo.repositories.RoomRepository;
import com.example.demo.repositories.UnavailableTermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ReservationController {
    private RoomRepository roomRepository;
    private ReservationRepository reservationRepository;
    private ReservationDetailsRepository reservationDetailsRepository;
    private UnavailableTermRepository unavailableTermRepository;

    @Autowired
    public ReservationController(RoomRepository roomRepository, ReservationRepository reservationRepository, UnavailableTermRepository unavailableTermRepository, ReservationDetailsRepository reservationDetailsRepository) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
        this.unavailableTermRepository = unavailableTermRepository;
        this.reservationDetailsRepository = reservationDetailsRepository;
    }

    @GetMapping("/summary")
       public String basicInfo(@RequestParam(name = "roomId") Long id, Model model){
        Reservation reservation = new Reservation();
        ReservationDetails reservationDetails = new ReservationDetails();
        Room room = roomRepository.getOne(id);
        model.addAttribute("room", room);
        model.addAttribute("reservation", reservation);
        model.addAttribute("reservationDetails", reservationDetails);

        return "summary";
    }

//    @GetMapping("/detailedInformation")
//    public String sad(@ModelAttribute Reservation reservation, @ModelAttribute ReservationDetails reservationDetails, Model model){
//         reservation.getFirstName();
//         reservationDetails.getEmail();
//        return "detailedInfo";
//    }



    @PostMapping("/summary/realize")
    public String realize(@ModelAttribute Reservation reservation, @ModelAttribute ReservationDetails reservationDetails, Model model){
        reservationDetails.setDateOfAddingReservation(LocalDate.now());
        reservation.setDetails(reservationDetails);
        reservationDetails.setReservation(reservation);
        reservationRepository.save(reservation);
        return "reservationDone";
    }

}
