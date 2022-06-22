package de.rieckpil;

import com.fasterxml.jackson.databind.JsonNode;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

  private final OrderRepository orderRepository;

  private static final Logger LOG = LoggerFactory.getLogger(OrderListener.class);

  public OrderListener(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @SqsListener("${order-queue-name}")
  public void processOrder(GenericMessage<JsonNode> payload) {
    LOG.info("Incoming order payload {}", payload);

    Order order = new Order();
    order.setCustomer(payload.getPayload().get("customer_name").asText());
    order.setAmount(payload.getPayload().get("order_amount").asLong());
    order.setDelivered(false);

    orderRepository.save(order);
  }
}
