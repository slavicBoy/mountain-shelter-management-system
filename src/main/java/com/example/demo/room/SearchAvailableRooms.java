package com.example.demo.room;

import com.example.demo.reservation.unavailableTerm.UnavailableTerm;
import com.example.demo.room.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SearchAvailableRooms {

    private RoomRepository roomRepository;
    private int theSmallerNumberAvailablePlaces = 1000;

    @Autowired
    public SearchAvailableRooms(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Map<Room, Integer> findAvailableRooms(LocalDate reservationDayStart, LocalDate reservationDayEnd, int numberOfPeople) {
        List<Room> rooms = roomRepository.findAll()
                .stream()
                .filter(room -> room.getForHowManyPeople() >= numberOfPeople)
                .collect(Collectors.toList());
        boolean isSpecificTermAvailable = true;

        Map<Room, Integer> roomWithAvailablePlaces = new HashMap<>();

        for (Room room : rooms) {
            List<UnavailableTerm> unavailableTerms = new ArrayList<>(room.getUnavailableTerms());

            int numberOfUnavailableTerms = unavailableTerms.size();
            if (numberOfUnavailableTerms == 0) {
                roomWithAvailablePlaces.put(room, room.getForHowManyPeople());
            } else {
                for (UnavailableTerm unavailableTerm : unavailableTerms) {
                    isSpecificTermAvailable = checkSpecificTerm(unavailableTerm, reservationDayStart, reservationDayEnd, numberOfPeople);

                    if (!isSpecificTermAvailable) {
                        break;
                    }

                }
                if (isSpecificTermAvailable) {
                    if (theSmallerNumberAvailablePlaces == 1000) {
                        roomWithAvailablePlaces.put(room, room.getForHowManyPeople());
                    } else {
                        roomWithAvailablePlaces.put(room, theSmallerNumberAvailablePlaces);
                    }
                }

                theSmallerNumberAvailablePlaces = 1000;
            }

        }
        return roomWithAvailablePlaces;
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

        if (unavailableTerm.getStartOfUnavailableTerm().isEqual(reservationDayStart)) {
            return ifPlacesAreAvailable(unavailableTerm, numberOfPeople);
        }

        return true;
    }


    private boolean ifPlacesAreAvailable(UnavailableTerm unavailableTerm, int numberOfPeople) {
        if (unavailableTerm.getPlacesAvailable() - numberOfPeople > -1) {
            if (unavailableTerm.getPlacesAvailable() < theSmallerNumberAvailablePlaces) {
                setTheSmallerNumberAvailablePlaces(unavailableTerm.getPlacesAvailable());
            }
            return true;
        } else {
            return false;
        }
    }


    public void setTheSmallerNumberAvailablePlaces(int theSmallerNumberAvailablePlaces) {
        this.theSmallerNumberAvailablePlaces = theSmallerNumberAvailablePlaces;
    }
}
