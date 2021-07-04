package com.example.demo.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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

    public List<RoomDto> findAvailableRooms(ArrivalDetailsDto arrivalDetails) {
        LocalDate reservationDayStart = arrivalDetails.getReservationDayStart();
        LocalDate reservationDayEnd = arrivalDetails.getReservationDayEnd();
        int numberOfPeople = arrivalDetails.getNumberOfPeople();
        List<RoomDto> availableRoomsDto = new ArrayList<>();
        Map<Room, Integer> availableRooms = searchAvailableRooms.findAvailableRooms(reservationDayStart, reservationDayEnd, numberOfPeople);
        for (Map.Entry<Room, Integer> roomIntegerEntry : availableRooms.entrySet()) {
            Room roomKey = roomIntegerEntry.getKey();
            RoomDto roomDto = RoomMapper.toDto(roomKey);
            roomDto.setPlacesLeft(roomIntegerEntry.getValue());
            availableRoomsDto.add(roomDto);
        }
        availableRoomsDto.forEach(System.out::println);

        return availableRoomsDto
                .stream()
                .sorted(Comparator.comparingInt(RoomDto::getForHowManyPeople))
                .collect(Collectors.toList());


    }

    public RoomDto findById(Long id) {
        return roomRepository
                .findById(id)
                .map(RoomMapper::toDto)
                .orElseThrow(() -> new RoomNotFoundException("Room does not exist with this id"));
    }

    public List<RoomDto> findAll() {
        return roomRepository.findAll()
                .stream().map(RoomMapper::toDto)
                .collect(Collectors.toList());
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
