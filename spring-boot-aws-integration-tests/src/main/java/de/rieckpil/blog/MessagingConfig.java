package de.rieckpil.blog;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.PayloadMethodArgumentResolver;

import java.util.Collections;

@Configuration
public class MessagingConfig {

  @Bean
  public QueueMessageHandlerFactory queueMessageHandlerFactory(@Autowired ObjectMapper objectMapper) {
    QueueMessageHandlerFactory factory = new QueueMessageHandlerFactory();
    MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
    messageConverter.setObjectMapper(objectMapper);
    factory.setArgumentResolvers(Collections.singletonList(new PayloadMethodArgumentResolver(messageConverter)));
    return factory;
  }

  @Bean
  public QueueMessagingTemplate queueMessagingTemplate(@Autowired AmazonSQSAsync amazonSQS) {
    return new QueueMessagingTemplate(amazonSQS);
  }
}
