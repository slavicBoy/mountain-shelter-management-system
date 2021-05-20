package com.example.demo.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<RoomDto> findAll() {
        return roomRepository.findAll()
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
