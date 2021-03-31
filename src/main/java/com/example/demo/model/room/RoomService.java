package com.example.demo.model.room;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repositories.RoomRepository;
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
        Room room = roomRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not exist with this id"));
        return RoomMapper.toDto(room);
    }
}
