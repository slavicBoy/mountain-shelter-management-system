package mountainshelter.controller;

import mountainshelter.model.Cart;
import mountainshelter.model.Client;
import mountainshelter.model.Room;
import mountainshelter.repository.ClientRepository;
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
import java.util.List;

@Controller
public class ReservationController {
    private RoomRepository roomRepository;
    private Cart cart;
    private ClientRepository clientRepository;
    @Autowired
    public ReservationController(RoomRepository roomRepository, Cart cart, ClientRepository clientRepository) {
        this.roomRepository = roomRepository;
        this.cart = cart;
        this.clientRepository = clientRepository;
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
        List<Room> rooms = cart.getRooms();
        client.setLocalDate(LocalDate.now());
        client.setRooms(rooms);
        clientRepository.save(client);
        return "reservationDone";
    }

}
