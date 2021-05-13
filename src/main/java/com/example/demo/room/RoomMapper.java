package com.example.demo.room;

public class RoomMapper {

    public static RoomDto toDto(Room room){
        RoomDto dto = new RoomDto();
        dto.setId(room.getId());
        dto.setDescription(room.getDescription());
        dto.setBathroom(room.isBathroom());
        dto.setForHowManyPeople(room.getForHowManyPeople());
        dto.setPricePerPeron(room.getPricePerPeron());
        dto.setImgUrl(room.getImgUrl());
        return dto;
    }


}
