package de.rieckpil;

import static org.awaitility.Awaitility.await;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.testcontainers.containers.localstack.LocalStackContainer.Service;
import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SQS;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import io.awspring.cloud.test.sqs.SqsTest;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@SqsTest(OrderListener.class)
class OrderListenerTest {

  @Container
  static LocalStackContainer localStack =
      new LocalStackContainer(DockerImageName.parse("localstack/localstack:0.14.3"))
          .withClasspathResourceMapping(
              "/localstack", "/docker-entrypoint-initaws.d", BindMode.READ_ONLY)
          .withServices(Service.SQS)
          .waitingFor(Wait.forLogMessage(".*Initialized\\.\n", 1));

  @DynamicPropertySource
  static void properties(DynamicPropertyRegistry registry) {
    registry.add(
        "spring.cloud.aws.sqs.endpoint", () -> localStack.getEndpointOverride(SQS).toString());
    registry.add("spring.cloud.aws.credentials.access-key", () -> "foo");
    registry.add("spring.cloud.aws.credentials.secret-key", () -> "bar");
    registry.add("spring.cloud.aws.region.static", () -> localStack.getRegion());
    registry.add("order-queue-name", () -> "test-order-queue");
  }

  @Autowired private SqsTemplate sqsTemplate;

  @MockBean private PurchaseOrderRepository purchaseOrderRepository;

  @Test
  void shouldStoreIncomingPurchaseOrderInDatabase() throws JsonProcessingException {

    sqsTemplate.send(
        "test-order-queue",
        new ObjectMapper().writeValueAsString(new PurchaseOrderPayload("duke", 42L)));

    await()
        .atMost(Duration.ofSeconds(3))
        .untilAsserted(() -> verify(purchaseOrderRepository).save(any(PurchaseOrder.class)));
  }
}
