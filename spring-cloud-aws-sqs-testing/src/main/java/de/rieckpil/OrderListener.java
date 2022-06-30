package de.rieckpil;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

  private final PurchaseOrderRepository purchaseOrderRepository;

  private static final Logger LOG = LoggerFactory.getLogger(OrderListener.class);

  public OrderListener(PurchaseOrderRepository purchaseOrderRepository) {
    this.purchaseOrderRepository = purchaseOrderRepository;
  }

  @SqsListener("${order-queue-name}")
  public void processOrder(@Payload ObjectNode payload, @Headers Map<String, Object> payloadHeaders) {
    LOG.info("Incoming order payload {} with headers {}", payload, payloadHeaders);

    PurchaseOrder purchaseOrder = new PurchaseOrder();
    purchaseOrder.setCustomer(payload.get("customer_name").asText());
    purchaseOrder.setAmount(payload.get("order_amount").asLong());
    purchaseOrder.setDelivered(false);

    purchaseOrderRepository.save(purchaseOrder);
  }
}
