package com.example.demo.model.reservation;

import com.example.demo.model.room.Room;
import com.example.demo.model.unavailableTerm.UnavailableTerm;
import com.example.demo.repositories.ReservationRepository;
import com.example.demo.repositories.RoomRepository;
import com.example.demo.repositories.UnavailableTermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


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

    public Reservation create(Reservation reservation, Long id) {
        Room room = roomRepository.getOne(id);
        UnavailableTerm unavailableTerm = checkAndSetDate.isRoomAvailable(room, roomRepository, reservation);
        if (unavailableTerm == null) {
            return null;
        }
        reservation.getDetails().setDateOfAddingReservation(LocalDate.now());
        reservation.setUnavailableTerm(unavailableTerm);
        reservation.setRoom(room);
        reservationRepository.save(reservation);
        return reservation;
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
