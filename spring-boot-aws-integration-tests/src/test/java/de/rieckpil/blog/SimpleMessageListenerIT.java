package de.rieckpil.blog;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.S3;
import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SQS;

@SpringBootTest
@Testcontainers
public class SimpleMessageListenerIT {

  @Container
  static LocalStackContainer localStack = new LocalStackContainer()
    .withServices(S3, SQS);

  @Autowired
  private QueueMessagingTemplate queueMessagingTemplate;

  @TestConfiguration
  static class AwsConfiguration {

    @Bean
    public AmazonS3 amazonS3() {
      AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
        .withCredentials(localStack.getDefaultCredentialsProvider())
        .withEndpointConfiguration(localStack.getEndpointConfiguration(S3))
        .build();

      amazonS3.createBucket("order-event-bucket2");

      return amazonS3;
    }

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync) {
      return new QueueMessagingTemplate(amazonSQSAsync);
    }

    @Bean
    @Primary
    public AmazonSQSAsync amazonSQSAsync() {
      AmazonSQSAsync amazonSQSAsync = AmazonSQSAsyncClientBuilder.standard()
        .withCredentials(localStack.getDefaultCredentialsProvider())
        .withEndpointConfiguration(localStack.getEndpointConfiguration(SQS))
        .build();

      amazonSQSAsync.createQueue("order-event-queue2");
      return amazonSQSAsync;
    }
  }

  @DynamicPropertySource
  static void awsProperties(DynamicPropertyRegistry registry) {
    registry.add("cloud.aws.region.static", () -> "us-east-1");
    registry.add("sqs.orderEventQueue", () -> localStack.getEndpointConfiguration(SQS).getServiceEndpoint() + "/queue/order-event-queue2");
    registry.add("s3.orderEventBucket", () -> "order-event-bucket2");
  }

  @Test
  public void testMessageShouldBeUploadedToS3OnceConsumed() throws JsonProcessingException {
    OrderEvent orderEvent = new OrderEvent(UUID.randomUUID().toString(), "MacBook", "42", LocalDateTime.now(), false);
    queueMessagingTemplate.convertAndSend("order-event-queue2", orderEvent);
  }

}
