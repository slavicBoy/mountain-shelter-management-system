package com.example.demo.model.reservation;


import com.example.demo.model.unavailableTerm.UnavailableTerm;
import com.example.demo.model.room.Room;
import com.example.demo.repositories.RoomRepository;
import com.example.demo.repositories.UnavailableTermRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class CheckAndSetDate {


    public UnavailableTerm isRoomAvailable(Room room, RoomRepository roomRepository, Reservation reservation, UnavailableTermRepository unavailableTermRepository) {
        LocalDate reservationDayStart = reservation.getReservationDayStart();
        LocalDate reservationDayEnd = reservation.getReservationDayEnd();
        List<UnavailableTerm> unavailableTerms = roomRepository.getOne(room.getId()).getUnavailableTerms();
        for (UnavailableTerm unavailableTerm : unavailableTerms) {
            if (unavailableTerm.getStartOfUnavailableTerm().isAfter(reservationDayStart)) {
                if (!reservationDayEnd.isBefore(unavailableTerm.getStartOfUnavailableTerm())) {
                    return null;
                }
            } else if (unavailableTerm.getStartOfUnavailableTerm().isBefore(reservationDayStart)) {
                if (!reservationDayStart.isAfter(unavailableTerm.getEndOfUnavailableTerm())) {
                    return null;
                }
            } else if (unavailableTerm.getStartOfUnavailableTerm().isEqual(reservationDayStart)) {
                return null;
            }
        }
        UnavailableTerm unavailableTerm = createUnavailableTerm(reservationDayStart, reservationDayEnd);
        setDatesOnRoom(roomRepository, unavailableTerm, room, reservation);
        return unavailableTerm;
    }

    private UnavailableTerm createUnavailableTerm(LocalDate reservationDayStart, LocalDate reservationDayEnd) {
        return new UnavailableTerm(reservationDayStart, reservationDayEnd);
    }

    public void setDatesOnRoom(RoomRepository roomRepository, UnavailableTerm unavailableTerm, Room room, Reservation reservation) {
        Room roomFromDataBase = roomRepository.getOne(room.getId());
        roomFromDataBase.addUnavailableTerm(unavailableTerm);
        unavailableTerm.setRoom(roomFromDataBase);
        unavailableTerm.setPlacesAvailable(room.getForHowManyPeople() - reservation.getHowManyPeople());
    }
}



