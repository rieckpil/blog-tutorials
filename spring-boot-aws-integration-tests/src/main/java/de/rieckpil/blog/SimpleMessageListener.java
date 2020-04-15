package de.rieckpil.blog;

import com.amazonaws.services.s3.AmazonS3;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class SimpleMessageListener {

  private final AmazonS3 amazonS3;
  private final ObjectMapper objectMapper;
  private final String orderEventBucket;

  public SimpleMessageListener(@Value("${s3.orderEventBucket}") String orderEventBucket,
                               AmazonS3 amazonS3,
                               ObjectMapper objectMapper) {
    this.amazonS3 = amazonS3;
    this.objectMapper = objectMapper;
    this.orderEventBucket = orderEventBucket;
  }

  @SqsListener(value = "${sqs.orderEventQueue}")
  public void processMessage(@Payload OrderEvent orderEvent) throws JsonProcessingException {
    System.out.println("Incoming order: " + orderEvent);
    amazonS3.putObject(orderEventBucket, orderEvent.getId(), objectMapper.writeValueAsString(orderEvent));
    System.out.println("Successfully uploaded order to S3");
  }
}
