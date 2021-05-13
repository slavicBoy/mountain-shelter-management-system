package com.example.demo.message;

import com.example.demo.user.User;
import com.example.demo.repositories.MessageRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private MessageRepository messageRepository;
    private UserRepository userRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public void sendMessage(Message message) {
        setTimeOnMessage(message);
        assignUserToMessage(message);
        messageRepository.save(message);
    }

    private void assignUserToMessage(Message message) {
        User userToAssign = userRepository.getOne(message.getUser().getId());
        message.setUser(userToAssign);
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
