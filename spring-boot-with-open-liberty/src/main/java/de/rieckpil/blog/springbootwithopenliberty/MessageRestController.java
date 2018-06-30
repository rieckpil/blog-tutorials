package de.rieckpil.blog.springbootwithopenliberty;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageRestController {

    private final MessageRepository messageRepository;

    public MessageRestController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/api/messages")
    public List<Message> getAllMessages() {

        return messageRepository.findAll();

    }
}
