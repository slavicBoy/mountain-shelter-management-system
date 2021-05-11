package com.example.demo.controller;

import com.example.demo.model.message.Message;
import com.example.demo.model.message.MessageDto;
import com.example.demo.model.message.MessageService;
import com.example.demo.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/")
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/chat/message")
    @PreAuthorize("hasRole('OWNER') or hasRole('WORKER')")
    public void addMessage(@RequestBody Message message) {
        System.out.println(message.toString());
        messageService.sendMessage(message);
    }
    @GetMapping("/chat/message")
    @PreAuthorize("hasRole('OWNER') or hasRole('WORKER')")
    public ResponseEntity<List<MessageDto>> getMessages(){
        return ResponseEntity.ok(messageService.getMessages());
    }


}
