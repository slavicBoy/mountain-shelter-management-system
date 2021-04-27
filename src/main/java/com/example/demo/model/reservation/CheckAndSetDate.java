package com.example.demo.model.reservation;


import com.example.demo.model.unavailableTerm.UnavailableTerm;
import com.example.demo.model.room.Room;
import com.example.demo.repositories.RoomRepository;
import com.example.demo.repositories.UnavailableTermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class CheckAndSetDate {

    private RoomRepository roomRepository;
    private boolean wasAnotherReservationAdded = false;
    int i = 0;

    @Autowired
    public CheckAndSetDate(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public UnavailableTerm isRoomAvailable(Room room, RoomRepository roomRepository, Reservation reservation) {
        LocalDate reservationDayStart = reservation.getReservationDayStart();
        LocalDate reservationDayEnd = reservation.getReservationDayEnd();
        List<UnavailableTerm> unavailableTerms = roomRepository.getOne(room.getId()).getUnavailableTerms();
        List<UnavailableTerm> dateToCompare = new ArrayList<>();
        for (UnavailableTerm unavailableTerm : unavailableTerms) {
            if (unavailableTerm.getStartOfUnavailableTerm().isAfter(reservationDayStart)) {
                if (!reservationDayEnd.isBefore(unavailableTerm.getStartOfUnavailableTerm())) {
                    dateToCompare.add(unavailableTerm);
                }
            }
            if (unavailableTerm.getStartOfUnavailableTerm().isBefore(reservationDayStart)) {
                if (!reservationDayStart.isAfter(unavailableTerm.getEndOfUnavailableTerm())) {
                    dateToCompare.add(unavailableTerm);
                }
            }

            if (unavailableTerm.getStartOfUnavailableTerm().isEqual(reservationDayStart)) {
                dateToCompare.add(unavailableTerm);
            }
        }

        if (dateToCompare.size() > 0) {
            int dateWithTheLeastAmountOfPeople = compareDate(dateToCompare);
            System.out.println(dateWithTheLeastAmountOfPeople);
            boolean arePlacesAvailable = arePlacesAvailable(reservation, dateWithTheLeastAmountOfPeople);
            if (!arePlacesAvailable)
                return null;
            else {
                UnavailableTerm unavailableTerm = createUnavailableTerm(reservationDayStart, reservationDayEnd);
                setDateOnRoomWhenAnotherReservation(dateWithTheLeastAmountOfPeople, unavailableTerm, room, reservation);
                return unavailableTerm;
            }
        }
        UnavailableTerm unavailableTerm = createUnavailableTerm(reservationDayStart, reservationDayEnd);
        setDateOnRoomWhenFirstReservation(unavailableTerm, room, reservation);
        return unavailableTerm;

    }

    private int compareDate(List<UnavailableTerm> dateToCompare) {
        return dateToCompare.stream()
                .flatMapToInt(x -> IntStream.of(x.getPlacesAvailable()))
                .min().getAsInt();
    }

    private boolean arePlacesAvailable(Reservation reservation, int dateWithTheLeastAmountOfPeople) {
        return dateWithTheLeastAmountOfPeople - reservation.getHowManyPeople() > 0;
    }

    private UnavailableTerm createUnavailableTerm(LocalDate reservationDayStart, LocalDate reservationDayEnd) {
        return new UnavailableTerm(reservationDayStart, reservationDayEnd);
    }

    public void setDateOnRoomWhenFirstReservation(UnavailableTerm unavailableTerm, Room room, Reservation reservation) {
        Room roomFromDataBase = roomRepository.getOne(room.getId());
        roomFromDataBase.addUnavailableTerm(unavailableTerm);
        unavailableTerm.setRoom(roomFromDataBase);
        unavailableTerm.setPlacesAvailable(room.getForHowManyPeople() - reservation.getHowManyPeople());
    }

    private void setDateOnRoomWhenAnotherReservation(int dateWithTheLastAmountOfPeople, UnavailableTerm unavailableTerm, Room room, Reservation reservation) {
        Room roomFromDataBase = roomRepository.getOne(room.getId());
        roomFromDataBase.addUnavailableTerm(unavailableTerm);
        unavailableTerm.setRoom(roomFromDataBase);
        unavailableTerm.setPlacesAvailable(dateWithTheLastAmountOfPeople - reservation.getHowManyPeople());

    }
}



