package de.rieckpil.blog;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testcontainers.containers.localstack.LocalStackContainer.Service.S3;
import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SQS;

@Testcontainers
@SpringBootTest
@Import(AwsTestConfig.class)
public class SimpleMessageListenerIT {

  @Container
  static LocalStackContainer localStack = new LocalStackContainer("0.10.0")
    .withServices(S3, SQS)
    .withEnv("DEFAULT_REGION", "eu-central-1");

  @BeforeAll
  static void beforeAll() throws IOException, InterruptedException {
    localStack.execInContainer("awslocal", "sqs", "create-queue", "--queue-name", QUEUE_NAME);
    localStack.execInContainer("awslocal", "s3", "mb", "s3://" + BUCKET_NAME);
  }

  @Autowired
  private AmazonS3 amazonS3;

  @Autowired
  private QueueMessagingTemplate queueMessagingTemplate;

  private static final String QUEUE_NAME = "order-event-test-queue";
  private static final String BUCKET_NAME = "order-event-test-bucket";

  @Test
  public void testMessageShouldBeUploadedToS3OnceConsumed() {
    String orderId = UUID.randomUUID().toString();
    OrderEvent orderEvent = new OrderEvent(orderId, "MacBook", "42", LocalDateTime.now(), false);

    queueMessagingTemplate.convertAndSend(QUEUE_NAME, orderEvent);

    given()
      .ignoreException(AmazonS3Exception.class)
      .await()
      .atMost(5, SECONDS)
      .untilAsserted(() -> assertNotNull(amazonS3.getObject(BUCKET_NAME, orderId)));

  }
}
