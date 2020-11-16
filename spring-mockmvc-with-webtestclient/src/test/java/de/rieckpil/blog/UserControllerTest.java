package de.rieckpil.blog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;

@WebMvcTest(UserController.class)
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  private WebTestClient webTestClient;

  @BeforeEach
  public void setup() {
    this.webTestClient = MockMvcWebTestClient
      .bindTo(mockMvc)
      .defaultHeader("X-Duke", "42")
      .filter(logRequest())
      .build();
  }

  @Test
  @WithMockUser(username = "duke")
  void test() {
    this.webTestClient
      .get()
      .uri("/api/users")
      .exchange()
      .expectStatus().is2xxSuccessful();
  }

  private ExchangeFilterFunction logRequest() {
    return (clientRequest, next) -> {
      System.out.printf("Request: %s %s %n", clientRequest.method(), clientRequest.url());
      return next.exchange(clientRequest);
    };
  }
}
