package de.rieckpil.blog;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  private final ObjectMapper objectMapper;

  public CustomerController(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @GetMapping(path = "/{id}")
  public JsonNode getCustomerById(@PathVariable("id") Long id) {
    return objectMapper.createObjectNode()
      .put("name", "duke")
      .put("customerId", id);
  }
}
