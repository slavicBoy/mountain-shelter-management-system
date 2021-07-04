package com.example.demo.chat.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/chat/message")
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    @PreAuthorize("hasRole('OWNER') or hasRole('WORKER')")
    public ResponseEntity<?> addMessage(@RequestBody MessageDto messageToSendDto) {
        MessageDto messageDto = messageService.sendMessage(messageToSendDto);
        return ResponseEntity.ok(messageDto);
    }
    @GetMapping
    @PreAuthorize("hasRole('OWNER') or hasRole('WORKER')")
    public ResponseEntity<?> getMessages(){

        return ResponseEntity.ok(messageService.getMessages());
    }


}
