package de.rieckpil.blog;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static de.rieckpil.blog.SimpleMessageListenerIT.localStack;
import static org.testcontainers.containers.localstack.LocalStackContainer.Service.S3;
import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SQS;

@TestConfiguration
public class AwsTestConfig {

  @Bean
  public AmazonS3 amazonS3() {
    return AmazonS3ClientBuilder.standard()
      .withCredentials(localStack.getDefaultCredentialsProvider())
      .withEndpointConfiguration(localStack.getEndpointConfiguration(S3))
      .build();
  }

  @Bean
  public AmazonSQSAsync amazonSQS() {
    return AmazonSQSAsyncClientBuilder.standard()
      .withCredentials(localStack.getDefaultCredentialsProvider())
      .withEndpointConfiguration(localStack.getEndpointConfiguration(SQS))
      .build();
  }
}
