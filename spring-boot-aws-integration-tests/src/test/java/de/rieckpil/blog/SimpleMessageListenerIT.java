package de.rieckpil.blog;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class SimpleMessageListenerIT {

  @Test
  public void testMe() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    OrderEvent orderEvent = new OrderEvent(UUID.randomUUID().toString(), "MacBook", "42", LocalDateTime.now(), false);
    System.out.println(objectMapper.writeValueAsString(orderEvent));
  }

}
