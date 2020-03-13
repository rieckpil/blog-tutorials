package de.rieckpil.blog;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ResourceClient {

  private final RestTemplate reqresRestTemplate;

  public ResourceClient(RestTemplate reqresRestTemplate) {
    this.reqresRestTemplate = reqresRestTemplate;
  }

  public JsonNode getSingleResource(Long id) {
    return this.reqresRestTemplate.getForObject("/api/unkown/{id}", JsonNode.class, id);
  }
}
