package de.rieckpil.blog;

import io.awspring.cloud.s3.S3Exception;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.io.IOException;
import java.util.Map;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testcontainers.containers.localstack.LocalStackContainer.Service.S3;
import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SQS;

@Testcontainers
@SpringBootTest
@Disabled
class SimpleMessageListenerPre23IT {

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
    registry.add("spring.cloud.aws.credentials.access-key", localStack::getAccessKey);
    registry.add("spring.cloud.aws.credentials.secret-key", localStack::getSecretKey);
    System.setProperty("aws.region", "eu-central-1");
    System.setProperty("aws.accessKeyId", "foo");
    System.setProperty("aws.secretAccessKey", "bar");
  }

  @TestConfiguration
  static class AwsTestConfig {

    @Bean
    public S3Client amazonS3() {
      return S3Client.builder()
        .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(localStack.getAccessKey(),  localStack.getSecretKey())))
        .endpointOverride(localStack.getEndpointOverride(S3))
        .build();
    }

    @Bean
    public SqsClient amazonSQS() {
      return SqsClient.builder()
        .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(localStack.getAccessKey(),  localStack.getSecretKey())))
        .endpointOverride(localStack.getEndpointOverride(SQS))
        .build();
    }
  }

  @Autowired
  private S3Client amazonS3;

  @Autowired
  private SqsTemplate queueMessagingTemplate;

  @Test
  void messageShouldBeUploadedToBucketOnceConsumedFromQueue() {

    queueMessagingTemplate.send(QUEUE_NAME, new GenericMessage<>("""
        {
           "id": "13",
           "message": "Please delivery ASAP",
           "product": "MacBook Pro",
           "orderedAt": "2021-11-11 12:00:00",
           "expressDelivery": true
        }
      """, Map.of("contentType", "application/json")));

    given()
      .ignoreException(S3Exception.class)
      .await()
      .atMost(5, SECONDS)
      .untilAsserted(() -> assertNotNull(amazonS3.getObject(GetObjectRequest.builder().bucket(BUCKET_NAME).key("13").build())));
  }
}
