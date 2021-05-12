package com.example.demo.model.message;

public class MessageMapper {

    public static MessageDto toDto(Message message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setContent(message.getContent());
        messageDto.setLikes(message.getLikes());
        messageDto.setLocalDate(message.getLocalDate());
        messageDto.setLocalTime(message.getLocalTime());
        messageDto.setFirstName(message.getUser().getFirstName());
        messageDto.setLastName(message.getUser().getLastName());
        messageDto.setUserId(message.getUser().getId());
        return messageDto;
    }




}
