package com.example.demo.model.reservation;

import com.example.demo.model.CheckAndSetDate;
import com.example.demo.model.UnavailableTerm;
import com.example.demo.model.room.Room;
import com.example.demo.repositories.ReservationRepository;
import com.example.demo.repositories.RoomRepository;
import com.example.demo.repositories.UnavailableTermRepository;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class ReservationService {
    private ReservationRepository reservationRepository;
    private RoomRepository roomRepository;
    private CheckAndSetDate checkAndSetDate;
    private UnavailableTermRepository unavailableTermRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, RoomRepository roomRepository, UnavailableTermRepository unavailableTermRepository, CheckAndSetDate checkAndSetDate) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.checkAndSetDate = checkAndSetDate;
        this.unavailableTermRepository = unavailableTermRepository;
    }

    public Reservation save(Reservation reservation, Long id) {
        Room room = roomRepository.getOne(id);
        boolean isRoomAvailable = checkAndSetDate.ifRoomAvailable(room, roomRepository, reservation, unavailableTermRepository);
        if (!isRoomAvailable) {
            return null;
        }
        reservation.getDetails().setDateOfAddingReservation(LocalDate.now());
        reservation.setRoom(room);
        reservationRepository.save(reservation);
        return reservation;
    }


}
