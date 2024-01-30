package de.rieckpil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.awspring.cloud.sqs.annotation.SqsListener;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

  private final PurchaseOrderRepository purchaseOrderRepository;

  private static final Logger LOG = LoggerFactory.getLogger(OrderListener.class);

  public OrderListener(PurchaseOrderRepository purchaseOrderRepository) {
    this.purchaseOrderRepository = purchaseOrderRepository;
  }

  @SqsListener("${order-queue-name}")
  public void processOrder(@Payload String rawPayload, @Headers Map<String, Object> payloadHeaders)
      throws JsonProcessingException {
    LOG.info("Incoming order payload {} with headers {}", rawPayload, payloadHeaders);

    ObjectNode payload = new ObjectMapper().readValue(rawPayload, ObjectNode.class);

    PurchaseOrder purchaseOrder = new PurchaseOrder();
    purchaseOrder.setCustomer(payload.get("customerName").asText());
    purchaseOrder.setAmount(payload.get("orderAmount").asLong());
    purchaseOrder.setDelivered(false);

    purchaseOrderRepository.save(purchaseOrder);
  }
}
