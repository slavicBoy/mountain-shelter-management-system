package com.example.demo.reservation;


import com.example.demo.reservation.unavailableTerm.DateUnavailableException;
import com.example.demo.reservation.unavailableTerm.UnavailableTerm;
import com.example.demo.room.Room;
import com.example.demo.room.RoomRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

@Component
public class ReservationModelLogic {

    private RoomRepository roomRepository;
    private UserRepository userRepository;

    @Autowired
    public ReservationModelLogic(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    public Optional<UnavailableTerm> isRoomAvailable(Room room, RoomRepository roomRepository, Reservation reservation) {
        LocalDate reservationDayStart = reservation.getReservationDayStart();
        LocalDate reservationDayEnd = reservation.getReservationDayEnd();
        List<UnavailableTerm> unavailableTerms = roomRepository.getOne(room.getId()).getUnavailableTerms();
        List<UnavailableTerm> dateToCompare = new ArrayList<>();
        if (reservation.getHowManyPeople() - room.getForHowManyPeople() > 0) {
            return Optional.empty();
        }

        for (UnavailableTerm unavailableTerm : unavailableTerms) {

            if (unavailableTerm.getStartOfUnavailableTerm().isAfter(reservationDayStart)) {

                if (reservationDayEnd.isEqual(unavailableTerm.getStartOfUnavailableTerm())) {
                    continue;
                }

                if (!reservationDayEnd.isBefore(unavailableTerm.getStartOfUnavailableTerm())) {
                    dateToCompare.add(unavailableTerm);
                }
            }
            if (unavailableTerm.getStartOfUnavailableTerm().isBefore(reservationDayStart)) {
                if (reservationDayStart.isEqual(unavailableTerm.getEndOfUnavailableTerm())) {
                    continue;
                }

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
            boolean arePlacesAvailable = arePlacesAvailable(reservation, dateWithTheLeastAmountOfPeople);
            if (!arePlacesAvailable) {
                return Optional.empty();
            } else {
                UnavailableTerm unavailableTerm = createUnavailableTerm(reservationDayStart, reservationDayEnd);
                setDateOnRoomWhenAnotherReservation(dateWithTheLeastAmountOfPeople, unavailableTerm, room, reservation);
                System.out.println("Places left: " + dateWithTheLeastAmountOfPeople);
                return Optional.of(unavailableTerm);
            }
        }
        System.out.println("First register on this term");
        UnavailableTerm unavailableTerm = createUnavailableTerm(reservationDayStart, reservationDayEnd);
        setDateOnRoomWhenFirstReservation(unavailableTerm, room, reservation);
        return Optional.of(unavailableTerm);

    }

    private int compareDate(List<UnavailableTerm> dateToCompare) {
        return dateToCompare.stream()
                .flatMapToInt(x -> IntStream.of(x.getPlacesAvailable()))
                .min().getAsInt();
    }

    private boolean arePlacesAvailable(Reservation reservation, int dateWithTheLeastAmountOfPeople) {
        return dateWithTheLeastAmountOfPeople - reservation.getHowManyPeople() >= 0;
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

    public void setUpdatedInfos(Reservation reservation, ReservationDto updatedReservationDto) {
        reservation.setFirstName(updatedReservationDto.getFirstName());
        reservation.setLastName(updatedReservationDto.getLastName());
        reservation.getDetails().setExtraInformation(updatedReservationDto.getExtraInformation());
        reservation.getDetails().setEmail(updatedReservationDto.getEmail());
        reservation.setPhoneNumber(updatedReservationDto.getPhoneNumber());
    }

    public void setDiscountOrPayment(Reservation reservation, Map<String, Boolean> updates) {
        reservation.getDetails().setWasDiscountShowed(updates.get("wasDiscountShowed"));
        reservation.getDetails().setAdvancePaid(updates.get("advancePaid"));
        reservation.getDetails().setAccommodationPaid(updates.get("accommodationPaid"));
    }

    public void addNewNotification() {
        for (User user : userRepository.findAll()) {
            user.incrementNotification();
            userRepository.save(user);
        }
    }

    public void setObligatoryInfoForEveryReservation(Reservation reservation, UnavailableTerm unavailableTerm, Room room) {
        reservation.getDetails().setDateOfAddingReservation(LocalDate.now());
        reservation.setUnavailableTerm(unavailableTerm);
        reservation.setRoom(room);
        reservation.getDetails().setWasDiscountShowed(false);
        reservation.getDetails().setAccommodationPaid(false);
        reservation.getDetails().setAdvancePaid(false);
    }

}





