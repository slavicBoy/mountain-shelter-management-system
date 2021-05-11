package com.example.demo.model.message;

import com.example.demo.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void sendMessage(Message message) {
        setTimeOnMessage(message);
        messageRepository.save(message);
    }

    private void setTimeOnMessage(Message message) {
        message.setLocalDate(LocalDate.now());
        message.setLocalTime(LocalTime.now());
    }

    public List<MessageDto> getMessages() {
        return messageRepository.findAll()
                .stream()
                .map(MessageMapper::toDto)
                .collect(Collectors.toList());
    }
}
