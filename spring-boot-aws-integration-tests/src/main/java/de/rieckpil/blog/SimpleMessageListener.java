package de.rieckpil.blog;

import com.amazonaws.services.s3.AmazonS3;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class SimpleMessageListener {

  private static final Logger LOG = LoggerFactory.getLogger(SimpleMessageListener.class);

  private final AmazonS3 amazonS3;
  private final ObjectMapper objectMapper;
  private final String orderEventBucket;

  public SimpleMessageListener(
    @Value("${event-processing.order-event-bucket}") String orderEventBucket,
    AmazonS3 amazonS3,
    ObjectMapper objectMapper) {
    this.amazonS3 = amazonS3;
    this.objectMapper = objectMapper;
    this.orderEventBucket = orderEventBucket;
  }

  @SqsListener(value = "${event-processing.order-event-queue}")
  public void processMessage(@Payload OrderEvent orderEvent) throws JsonProcessingException {
    LOG.info("Incoming order: '{}'", orderEvent);

    amazonS3.putObject(orderEventBucket, orderEvent.getId(), objectMapper.writeValueAsString(orderEvent));

    LOG.info("Successfully uploaded order to S3");
  }
}
