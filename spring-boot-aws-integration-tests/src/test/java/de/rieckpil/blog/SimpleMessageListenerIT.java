package de.rieckpil.blog;

import com.amazonaws.services.s3.AmazonS3;
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
import static org.awaitility.Awaitility.await;
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
    localStack.execInContainer("awslocal", "sqs", "create-queue", "--queue-name", "order-event-queue");
    localStack.execInContainer("awslocal", "s3", "mb", "s3://order-event-bucket");
  }

  @Autowired
  private AmazonS3 amazonS3;

  @Autowired
  private QueueMessagingTemplate queueMessagingTemplate;

  @Test
  public void testMessageShouldBeUploadedToS3OnceConsumed() {
    String orderId = UUID.randomUUID().toString();
    OrderEvent orderEvent = new OrderEvent(orderId, "MacBook", "42", LocalDateTime.now(), false);

    queueMessagingTemplate.convertAndSend("order-event-queue", orderEvent);

    await().atMost(5, SECONDS).until(() -> amazonS3.getObject("order-event-bucket", orderId) != null);
  }

}
