package mountainshelter.controller;

import mountainshelter.model.*;
import mountainshelter.repository.ClientRepository;
import mountainshelter.repository.DateRepository;
import mountainshelter.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.undo.CannotUndoException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

@Controller
public class ReservationController {
    private RoomRepository roomRepository;
    private Cart cart;
    private ClientRepository clientRepository;
    private DateRepository dateRepository;
    @Autowired
    public ReservationController(RoomRepository roomRepository, Cart cart, ClientRepository clientRepository, DateRepository dateRepository) {
        this.roomRepository = roomRepository;
        this.cart = cart;
        this.clientRepository = clientRepository;
        this.dateRepository =  dateRepository;
    }

    @GetMapping("/reserve")
    public String reserveRoom(@RequestParam(name = "roomId") Long roomId, Model model) {
        Room room = roomRepository.getOne(roomId);
        cart.addRoomToCart(room);
        return "roomReserved";
    }
    @GetMapping("/summary")
       public String reservationSummary(Model model){
        Client client = new Client();
        List<Room> rooms = cart.getRooms();
        model.addAttribute("rooms", rooms);
        model.addAttribute("client", client);
        return "summary";
    }
    @PostMapping("/summary/realize")
      public String summaryReservation(@ModelAttribute Client client){
        Date data = new Date(client.getReservationDayStart(), client.getReservationDayEnd());
        CheckAndSetDate checkAndSetDate = new CheckAndSetDate();
        List<Room> rooms = cart.getRooms();
        boolean b = checkAndSetDate.ifRoomsAvailable(data, rooms, roomRepository, dateRepository);
        System.out.println(b);
        client.setRooms(rooms);
        clientRepository.save(client);
//        DateSetter.setDatesOnRooms(roomRepository, rooms, data);
        cart.clear();
        return "reservationDone";
    }

}
