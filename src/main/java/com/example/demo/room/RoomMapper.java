package com.example.demo.room;

public class RoomMapper {

    public static RoomDto toDto(Room room){
        return RoomDto.getBuilder()
                .id(room.getId())
                .isBathroom(room.isBathroom())
                .forHowManyPeople(room.getForHowManyPeople())
                .pricePerPerson(room.getPricePerPeron())
                .imgUrl(room.getImgUrl())
                .roomNumber(room.getRoomNumber())
                .description(room.getDescription())
                .build();
    }
}

