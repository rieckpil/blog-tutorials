package de.rieckpil.blog;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhooks")
public class WebhookController {

  private static final Logger LOG = LoggerFactory.getLogger(WebhookController.class);

  @PostMapping("/orders")
  public ResponseEntity<Void> handleOrderWebhook(@RequestBody ObjectNode objectNode) {

    LOG.info("Incoming webhook payload for orders: '{}'", objectNode);

    // further order handling

    return ResponseEntity.noContent().build();
  }
}
