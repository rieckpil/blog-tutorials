package de.rieckpil.blog;

import io.awspring.cloud.s3.S3Exception;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;

import java.io.IOException;
import java.time.LocalDateTime;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testcontainers.containers.localstack.LocalStackContainer.Service.S3;
import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SQS;

@Testcontainers
@SpringBootTest
class SimpleMessageListenerIT {

  private static final String QUEUE_NAME = "order-event-test-queue";
  private static final String BUCKET_NAME = "order-event-test-bucket";

  @Container
  static LocalStackContainer localStack =
    new LocalStackContainer(DockerImageName.parse("localstack/localstack:0.13.0"))
      .withServices(S3, SQS);

  @BeforeAll
  static void beforeAll() throws IOException, InterruptedException {
    localStack.execInContainer("awslocal", "sqs", "create-queue", "--queue-name", QUEUE_NAME);
    localStack.execInContainer("awslocal", "s3", "mb", "s3://" + BUCKET_NAME);
  }

  @DynamicPropertySource
  static void overrideConfiguration(DynamicPropertyRegistry registry) {
    registry.add("event-processing.order-event-queue", () -> QUEUE_NAME);
    registry.add("event-processing.order-event-bucket", () -> BUCKET_NAME);
    registry.add("spring.cloud.aws.sqs.endpoint", () -> localStack.getEndpointOverride(SQS));
    registry.add("spring.cloud.aws.s3.endpoint", () -> localStack.getEndpointOverride(S3));
    registry.add("spring.cloud.aws.credentials.access-key", localStack::getAccessKey);
    registry.add("spring.cloud.aws.credentials.secret-key", localStack::getSecretKey);
  }

  @Autowired
  private S3Client amazonS3;

  @Autowired
  private SqsTemplate sqsTemplate;

  @Test
  void messageShouldBeUploadedToBucketOnceConsumedFromQueue() {

    sqsTemplate.send(QUEUE_NAME, new GenericMessage<>(new OrderEvent("42", "MacBook Pro", "Please delivery ASAP", LocalDateTime.now().plusDays(4), false)));

    given()
      .ignoreException(NoSuchKeyException.class)
      .await()
      .atMost(5, SECONDS)
      .untilAsserted(() -> assertNotNull(amazonS3.getObject(GetObjectRequest.builder().bucket(BUCKET_NAME).key("42").build())));
  }
}
