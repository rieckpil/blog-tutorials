package de.rieckpil;

import java.time.Duration;
import java.util.Map;

import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import io.awspring.cloud.test.sqs.SqsTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.testcontainers.containers.localstack.LocalStackContainer.*;
import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SQS;

@Testcontainers
@SqsTest(OrderListener.class)
class OrderListenerTest {

  @Container
  static LocalStackContainer localStack = new LocalStackContainer(DockerImageName.parse("localstack/localstack:0.14.3"))
    .withClasspathResourceMapping("/localstack", "/docker-entrypoint-initaws.d", BindMode.READ_ONLY)
    .withServices(Service.SQS)
    .waitingFor(Wait.forLogMessage(".*Initialized\\.\n", 1));

  @DynamicPropertySource
  static void properties(DynamicPropertyRegistry registry) {
    registry.add("cloud.aws.sqs.endpoint", () -> localStack.getEndpointOverride(SQS).toString());
    registry.add("cloud.aws.credentials.access-key", () -> "foo");
    registry.add("cloud.aws.credentials.secret-key", () -> "bar");
    registry.add("cloud.aws.region.static", () -> localStack.getRegion());
    registry.add("order-queue-name", () -> "test-order-queue");
  }

  @Autowired
  private QueueMessagingTemplate queueMessagingTemplate;

  @MockBean
  private PurchaseOrderRepository purchaseOrderRepository;

  @Test
  void shouldStoreIncomingPurchaseOrderInDatabase() {

    Map<String, Object> messageHeaders = Map.of("contentType", "application/json");

    String payload = """
      {
       "customer_name": "duke",
       "order_amount": 42
      }
      """;

    queueMessagingTemplate
      .send("test-order-queue", new GenericMessage<>(payload, messageHeaders));

    await()
      .atMost(Duration.ofSeconds(3))
      .untilAsserted(() -> verify(purchaseOrderRepository).save(any(PurchaseOrder.class)));
  }
}
