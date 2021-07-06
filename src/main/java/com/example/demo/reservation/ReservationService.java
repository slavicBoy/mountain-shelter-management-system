package com.example.demo.reservation;

import com.example.demo.reservation.exception.ReservationNotFoundException;
import com.example.demo.reservation.repository.ReservationRepository;
import com.example.demo.reservation.unavailableTerm.DateUnavailableException;
import com.example.demo.room.RoomNotFoundException;
import com.example.demo.room.Room;
import com.example.demo.reservation.unavailableTerm.UnavailableTerm;
import com.example.demo.user.User;
import com.example.demo.room.RoomRepository;
import com.example.demo.reservation.unavailableTerm.UnavailableTermRepository;
import com.example.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ReservationService {
    private ReservationRepository reservationRepository;
    private RoomRepository roomRepository;
    private ReservationModelLogic reservationModelLogic;
    private UnavailableTermRepository unavailableTermRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    public ReservationService(ReservationRepository reservationRepository, RoomRepository roomRepository, UnavailableTermRepository unavailableTermRepository, ReservationModelLogic checkAndSetDate) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.reservationModelLogic = checkAndSetDate;
        this.unavailableTermRepository = unavailableTermRepository;
    }

    public ReservationDto create(Reservation reservation, Long id) {
        Room room;

        room = roomRepository
                .findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room does not exist with this id"));


        Optional<UnavailableTerm> unavailableTermOptional = reservationModelLogic.isRoomAvailable(room, roomRepository, reservation);
        if (unavailableTermOptional.isEmpty()) {
            throw new DateUnavailableException("That date is unavailable");
        }
        UnavailableTerm unavailableTerm = unavailableTermOptional.get();
        reservation.getDetails().setDateOfAddingReservation(LocalDate.now());
        reservation.setUnavailableTerm(unavailableTerm); ////// W ENCJI REZERWACJA
        reservation.setRoom(room);
        reservation.getDetails().setWasDiscountShowed(false);
        reservation.getDetails().setAccommodationPaid(false);
        reservation.getDetails().setAdvancePaid(false);
        addNewNotification();
        reservationRepository.save(reservation);
        return ReservationMapper.toDto(reservation);


    }


    private void addNewNotification() {
        for (User user : userRepository.findAll()) {
            user.incrementNotification();
            userRepository.save(user);
        }
    }

    public List<ReservationDto> findAll() {
        return reservationRepository.findAll()
                .stream()
                .map(ReservationMapper::toDto)
                .collect(Collectors.toList());
    }

    public ReservationDto findById(Long id) {
        return ReservationMapper.toDto(reservationRepository.getOne(id));
    }

    public ReservationDto updateReservation(Long id, Reservation updatedReservation) {

        Reservation reservation = reservationRepository
                .findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation does not exist with this id"));

        reservation.setFirstName(updatedReservation.getFirstName());
        reservation.setLastName(updatedReservation.getLastName());
        reservation.getDetails().setExtraInformation(updatedReservation.getDetails().getExtraInformation());
        reservation.getDetails().setEmail(updatedReservation.getDetails().getEmail());
        reservation.setPhoneNumber(updatedReservation.getPhoneNumber());
        reservationRepository.save(reservation);


        return ReservationMapper.toDto(reservation);


    }


    public ReservationDto confirmPaymentOrDiscount(Long id, Map<String, Boolean> updates) {

        Reservation reservation = reservationRepository
                .findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation does not exist with this id"));

        reservation.getDetails().setWasDiscountShowed(updates.get("wasDiscountShowed"));
        reservation.getDetails().setAdvancePaid(updates.get("advancePaid"));
        reservation.getDetails().setAccommodationPaid(updates.get("accommodationPaid"));
        reservationRepository.save(reservation);

        return ReservationMapper.toDto(reservation);
    }


    public ReservationDto deleteReservation(Long id) {
        Reservation reservation = reservationRepository
                .findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation does not exist with this id"));

        reservationRepository.delete(reservation);
        return ReservationMapper.toDto(reservation);

    }
}
