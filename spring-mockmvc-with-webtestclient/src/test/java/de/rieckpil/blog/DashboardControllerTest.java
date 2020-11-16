package de.rieckpil.blog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockUser;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(DashboardController.class)
class DashboardControllerTest {

  @Autowired
  private MockMvc mockMvc;

  private WebTestClient webTestClient;

  @BeforeEach
  void setup() {
    this.webTestClient = MockMvcWebTestClient
      .bindTo(mockMvc)
      .build();
  }

  @Test
  void shouldReturnDashboardViewWithDefaultModel() throws Exception {

    EntityExchangeResult<byte[]> result = this.webTestClient
      .get()
      .uri("/dashboard")
      .exchange()
      .expectStatus().is2xxSuccessful()
      .expectBody().returnResult();

    MockMvcWebTestClient.resultActionsFor(result)
      .andExpect(model().size(2))
      .andExpect(model().attributeExists("message"))
      .andExpect(model().attributeExists("orderIds"))
      .andExpect(model().attribute("message", "Hello World!"))
      .andExpect(view().name("dashboard"));
  }
}
