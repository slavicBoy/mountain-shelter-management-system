package com.example.demo.model.user;

import com.example.demo.model.reservation.Reservation;
import com.example.demo.model.reservation.ReservationDto;

public class UserMapper {

    public static UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
