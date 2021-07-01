package com.example.demo.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private RoomRepository roomRepository;
    private SearchAvailableRooms searchAvailableRooms;

    @Autowired
    public RoomService(RoomRepository roomRepository, SearchAvailableRooms searchAvailableRooms) {
        this.roomRepository = roomRepository;
        this.searchAvailableRooms = searchAvailableRooms;
    }

    public List<RoomDto> findAll(ArrivalDetailsDto arrivalDetails) {
        LocalDate reservationDayStart = arrivalDetails.getReservationDayStart();
        LocalDate reservationDayEnd = arrivalDetails.getReservationDayEnd();
        int numberOfPeople = arrivalDetails.getNumberOfPeople();

        List<Room> availableRooms = searchAvailableRooms.findAvailableRooms(reservationDayStart, reservationDayEnd, numberOfPeople)
                .stream()
                .sorted(Comparator.comparing(Room::getForHowManyPeople))
                .collect(Collectors.toList());

        return availableRooms
                .stream()
                .map(RoomMapper::toDto)
                .collect(Collectors.toList());


    }

    public RoomDto findById(Long id) {
        return roomRepository
                .findById(id)
                .map(RoomMapper::toDto)
                .orElseThrow(() -> new RoomNotFoundException("Room does not exist with this id"));
    }


/*    public Optional<RoomDto> findById(Long id) {
        try {
            Room room = roomRepository
                    .findById(id)
                    .orElseThrow(() -> new RoomNotFoundException("Room does not exist with this id"));
            return Optional.of(RoomMapper.toDto(room));
        } catch (RoomNotFoundException e) {
            System.err.println("Room does not exist with this id");
            return Optional.empty();
        }
    }*/
}
