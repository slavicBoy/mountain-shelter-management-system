package com.example.demo.chat.message;

import com.example.demo.chat.message.Message;
import com.example.demo.chat.message.MessageDto;

public class MessageMapper {

    public static MessageDto toDto(Message message) {
        return MessageDto.getBuilder()
                .firstName(message.getUser().getFirstName())
                .lastName(message.getUser().getLastName())
                .userId(message.getId())
                .likes(message.getLikes())
                .localTime(message.getLocalTime())
                .localDate(message.getLocalDate())
                .content(message.getContent())
                .build();
    }

// messageDto.setContent(message.getContent());
//        messageDto.setLikes(message.getLikes());
//        messageDto.setLocalDate(message.getLocalDate());
//        messageDto.setLocalTime(message.getLocalTime());
//        messageDto.setFirstName(message.getUser().getFirstName());
//        messageDto.setLastName(message.getUser().getLastName());
//        messageDto.setUserId(message.getUser().getId());


}
