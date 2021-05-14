package com.example.demo.message;

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
    public ResponseEntity<?> addMessage(@RequestBody Message messageToSend) {
        Message message = messageService.sendMessage(messageToSend);
        return ResponseEntity.ok(message);
    }
    @GetMapping("/chat/message")
    @PreAuthorize("hasRole('OWNER') or hasRole('WORKER')")
    public ResponseEntity<List<MessageDto>> getMessages(){
        return ResponseEntity.ok(messageService.getMessages());
    }


}
