package de.rieckpil.blog.springbootwithopenliberty;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class MessageWebSocketController {

    private final MessageRepository messageRepository;

    public MessageWebSocketController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message receiveNewChatMessage(@Valid ChatMessage chatMessage) {

        Message newMessage = new Message();
        newMessage.setContent(chatMessage.getContent());
        newMessage.setUser(chatMessage.getUsername());

        messageRepository.save(newMessage);

        return newMessage;
    }
}
