package com.example.demo.chat.message;

import com.example.demo.chat.message.Message;
import com.example.demo.chat.message.MessageDto;
import com.example.demo.user.User;

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

    public static Message toEntity(MessageDto messageDto){
        Message message = new Message();
        message.setContent(messageDto.getContent());
        message.setUser(new User());
        message.getUser().setId(messageDto.getUserId());
        return message;
    }


}
