package com.example.demo.reservation;

import com.example.demo.exception.RoomNotFoundException;
import com.example.demo.room.Room;
import com.example.demo.unavailableTerm.UnavailableTerm;
import com.example.demo.user.User;
import com.example.demo.repositories.ReservationRepository;
import com.example.demo.repositories.RoomRepository;
import com.example.demo.repositories.UnavailableTermRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ReservationService {
    private ReservationRepository reservationRepository;
    private RoomRepository roomRepository;
    private CheckAndSetDate checkAndSetDate;
    private UnavailableTermRepository unavailableTermRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    public ReservationService(ReservationRepository reservationRepository, RoomRepository roomRepository, UnavailableTermRepository unavailableTermRepository, CheckAndSetDate checkAndSetDate) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.checkAndSetDate = checkAndSetDate;
        this.unavailableTermRepository = unavailableTermRepository;
    }

    public Optional<Reservation> create(Reservation reservation, Long id) {
        Room room = roomRepository
                .findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room does not exist with this id"));

        Optional<UnavailableTerm> unavailableTermOptional = checkAndSetDate.isRoomAvailable(room, roomRepository, reservation);
        if (unavailableTermOptional.isEmpty()) {
            throw new RoomNotFoundException("Date is unavailable");
        } else {
            UnavailableTerm unavailableTerm = unavailableTermOptional.get();
            reservation.getDetails().setDateOfAddingReservation(LocalDate.now());
            reservation.setUnavailableTerm(unavailableTerm);
            reservation.setRoom(room);
            reservationRepository.save(reservation);
            addNewNotification();
        }
        return Optional.of(reservation);
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
        Reservation reservation = reservationRepository.getOne(id);
        reservation.setFirstName(updatedReservation.getFirstName());
        reservation.setLastName(updatedReservation.getLastName());
        reservation.getDetails().setExtraInformation(updatedReservation.getDetails().getExtraInformation());
        reservation.getDetails().setEmail(updatedReservation.getDetails().getEmail());
        reservation.setPhoneNumber(updatedReservation.getPhoneNumber());
        reservationRepository.save(reservation);
        return ReservationMapper.toDto(reservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
