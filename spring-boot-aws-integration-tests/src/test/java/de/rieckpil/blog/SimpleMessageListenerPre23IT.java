package de.rieckpil.blog;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import org.junit.jupiter.api.BeforeAll;
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

import java.io.IOException;
import java.util.Map;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testcontainers.containers.localstack.LocalStackContainer.Service.S3;
import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SQS;

@Testcontainers
@SpringBootTest
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
    registry.add("cloud.aws.credentials.access-key", localStack::getAccessKey);
    registry.add("cloud.aws.credentials.secret-key", localStack::getSecretKey);
  }

  @TestConfiguration
  static class AwsTestConfig {

    @Bean
    public AmazonS3 amazonS3() {
      return AmazonS3ClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(localStack.getAccessKey(),  localStack.getSecretKey())))
        .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(localStack.getEndpointOverride(S3).toString(), "eu-central-1"))
        .build();
    }

    @Bean
    public AmazonSQSAsync amazonSQS() {
      return AmazonSQSAsyncClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(localStack.getAccessKey(),  localStack.getSecretKey())))
        .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(localStack.getEndpointOverride(SQS).toString(), "eu-central-1"))
        .build();
    }
  }

  @Autowired
  private AmazonS3 amazonS3;

  @Autowired
  private QueueMessagingTemplate queueMessagingTemplate;

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
      .ignoreException(AmazonS3Exception.class)
      .await()
      .atMost(5, SECONDS)
      .untilAsserted(() -> assertNotNull(amazonS3.getObject(BUCKET_NAME, "13")));
  }
}
