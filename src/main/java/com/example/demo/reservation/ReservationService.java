package com.example.demo.reservation;

import com.example.demo.exception.DateUnavailableException;
import com.example.demo.exception.RoomNotFoundException;
import com.example.demo.exception.ReservationNotFoundException;
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

    public Optional<ReservationDto> create(Reservation reservation, Long id) {
        Room room;
        try {
            room = roomRepository
                    .findById(id)
                    .orElseThrow(() -> new RoomNotFoundException("Room does not exist with this id"));
        } catch (RoomNotFoundException e) {
            System.err.println("Room does not exist with this id");
            return Optional.empty();
        }

        try {
            Optional<UnavailableTerm> unavailableTermOptional = checkAndSetDate.isRoomAvailable(room, roomRepository, reservation);
            if (unavailableTermOptional.isPresent()) {
                UnavailableTerm unavailableTerm = unavailableTermOptional.get();
                reservation.getDetails().setDateOfAddingReservation(LocalDate.now());
                reservation.setUnavailableTerm(unavailableTerm);
                reservation.setRoom(room);
                addNewNotification();
                reservationRepository.save(reservation);
                ReservationDto reservationDto = ReservationMapper.toDto(reservation);
                return Optional.of(reservationDto);
            }
            return Optional.empty();
        } catch (DateUnavailableException e) {
            return Optional.empty();
        }
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

    public Optional<ReservationDto> updateReservation(Long id, Reservation updatedReservation) {
        try {
            Reservation reservation = reservationRepository
                    .findById(id)
                    .orElseThrow(() -> new ReservationNotFoundException("Reservation does not exist with this id"));

            reservation.setFirstName(updatedReservation.getFirstName());
            reservation.setLastName(updatedReservation.getLastName());
            reservation.getDetails().setExtraInformation(updatedReservation.getDetails().getExtraInformation());
            reservation.getDetails().setEmail(updatedReservation.getDetails().getEmail());
            reservation.setPhoneNumber(updatedReservation.getPhoneNumber());
            reservationRepository.save(reservation);

            return Optional.of(ReservationMapper.toDto(reservation));
        } catch (RoomNotFoundException e){
            System.err.println("Reservation does not exist with this id");
            return Optional.empty();
        }

    }

    public Optional<ReservationDto> deleteReservation(Long id) {
        try{
            Reservation reservation = reservationRepository
                    .findById(id)
                    .orElseThrow(() -> new ReservationNotFoundException("Reservation does not exist with this id"));
            reservationRepository.delete(reservation);
            return Optional.of(ReservationMapper.toDto(reservation));
        } catch (ReservationNotFoundException e) {
            System.err.println("Reservation does not exist with this id");
            return Optional.empty();
        }
    }
}
