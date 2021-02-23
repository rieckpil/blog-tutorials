package de.rieckpil.blog;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.containers.wait.strategy.WaitStrategy;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service;

@Testcontainers
public class LocalStackMountTest {

@Container
static LocalStackContainer container = new LocalStackContainer(DockerImageName.parse("localstack/localstack:0.12.6"))
  .withClasspathResourceMapping("/localstack", "/docker-entrypoint-initaws.d", BindMode.READ_ONLY)
  .withServices(Service.S3, Service.SQS)
  .waitingFor(Wait.forLogMessage(".*Initialized\\.\n", 1));

  @Test
  void accessLocalS3Bucket() {
    System.out.println("X");

    AmazonS3 s3Client = AmazonS3ClientBuilder
      .standard()
      .withCredentials(container.getDefaultCredentialsProvider())
      .withEndpointConfiguration(container.getEndpointConfiguration(Service.S3))
      .build();

    System.out.println(s3Client.getBucketLocation("testcontainers"));
  }
}
