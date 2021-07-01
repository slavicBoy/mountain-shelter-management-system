package com.example.demo.room;

import com.example.demo.reservation.unavailableTerm.UnavailableTerm;
import com.example.demo.room.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchAvailableRooms {

    private RoomRepository roomRepository;


    @Autowired
    public SearchAvailableRooms(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAvailableRooms(LocalDate reservationDayStart, LocalDate reservationDayEnd, int numberOfPeople) {

        List<Room> rooms = roomRepository.findAll()
                .stream()
                .filter(room -> room.getForHowManyPeople() >= numberOfPeople)
                .collect(Collectors.toList());

        List<Room> availableRooms = new ArrayList<>();

        boolean isSpecificTermAvailable = true;

        for (Room room : rooms) {
            List<UnavailableTerm> unavailableTerms = new ArrayList<>(room.getUnavailableTerms());

            for (UnavailableTerm unavailableTerm : unavailableTerms) {
                isSpecificTermAvailable = checkSpecificTerm(unavailableTerm, reservationDayStart, reservationDayEnd, numberOfPeople);
                if (!isSpecificTermAvailable) {
                    break;
                }
            }

            if(!isSpecificTermAvailable){
                isSpecificTermAvailable = true;
                continue;
            } else {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }


    public boolean checkSpecificTerm(UnavailableTerm unavailableTerm, LocalDate reservationDayStart, LocalDate reservationDayEnd, int numberOfPeople) {

        if (unavailableTerm.getStartOfUnavailableTerm().isAfter(reservationDayStart)) {

            if (reservationDayEnd.isEqual(unavailableTerm.getStartOfUnavailableTerm())) {
                return true;

            }

            if (!reservationDayEnd.isBefore(unavailableTerm.getStartOfUnavailableTerm())) {
                return ifPlacesAreAvailable(unavailableTerm, numberOfPeople);
            }
        }

        if (unavailableTerm.getStartOfUnavailableTerm().isBefore(reservationDayStart)) {

            if (reservationDayStart.isEqual(unavailableTerm.getEndOfUnavailableTerm())) {
                return true;
            }

            if (!reservationDayStart.isAfter(unavailableTerm.getEndOfUnavailableTerm())) {
                return ifPlacesAreAvailable(unavailableTerm, numberOfPeople);

            }
        }

        return !unavailableTerm.getStartOfUnavailableTerm().isEqual(reservationDayStart);

    }

    private boolean ifPlacesAreAvailable(UnavailableTerm unavailableTerm, int numberOfPeople) {
          return unavailableTerm.getPlacesAvailable() >= numberOfPeople;
    }


}
