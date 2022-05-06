package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = WebhookController.class, properties = "valid-api-key=test42")
class WebhookControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldForbidAccessWithMissingApiKey() throws Exception {
    this.mockMvc
      .perform(post("/webhooks/orders")
        .contentType(APPLICATION_JSON)
        .content("""
           {
            "orderId": 42
           }
          """)
      )
      .andExpect(status().isForbidden());
  }

  @Test
  void shouldForbidAccessWithInvalidApiKey() throws Exception {
    this.mockMvc
      .perform(post("/webhooks/orders")
        .header("X-API-KEY", "invalid42")
        .contentType(APPLICATION_JSON)
        .content("""
           {
            "orderId": 42
           }
          """)
      )
      .andExpect(status().isForbidden());
  }

  @Test
  void shouldAllowAccessWithValidApiKey() throws Exception {
    this.mockMvc
      .perform(post("/webhooks/orders")
        .header("X-API-KEY", "test42")
        .contentType(APPLICATION_JSON)
        .content("""
           {
            "orderId": 42
           }
          """)
      )
      .andExpect(status().isNoContent());
  }
}
