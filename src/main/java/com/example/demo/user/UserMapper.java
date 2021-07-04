package com.example.demo.user;

public class UserMapper {

    public static UserDto toDto(User user) {
        return UserDto.getBuilder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}
