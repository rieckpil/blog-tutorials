package de.rieckpil.blog;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@Configuration
public class MessagingConfig {

  @Bean
  public MappingJackson2MessageConverter mappingJackson2MessageConverter(@Autowired ObjectMapper objectMapper) {
    MappingJackson2MessageConverter jackson2MessageConverter = new MappingJackson2MessageConverter();
    jackson2MessageConverter.setObjectMapper(objectMapper);
    return jackson2MessageConverter;
  }

  @Bean
  public QueueMessagingTemplate queueMessagingTemplate(@Autowired AmazonSQSAsync amazonSQS) {
    return new QueueMessagingTemplate(amazonSQS);
  }
}
