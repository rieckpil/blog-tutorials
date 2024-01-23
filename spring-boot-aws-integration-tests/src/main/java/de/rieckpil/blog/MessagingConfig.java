package de.rieckpil.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@Configuration
public class MessagingConfig {

  @Bean
  public MappingJackson2MessageConverter mappingJackson2MessageConverter(ObjectMapper objectMapper) {
    MappingJackson2MessageConverter jackson2MessageConverter = new MappingJackson2MessageConverter();
    jackson2MessageConverter.setObjectMapper(objectMapper);
    return jackson2MessageConverter;
  }

}
