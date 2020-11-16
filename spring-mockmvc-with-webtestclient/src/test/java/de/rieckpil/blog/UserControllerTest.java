package de.rieckpil.blog;

import org.hamcrest.Matchers;
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

import java.util.List;

import static org.mockito.Mockito.when;

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
  void shouldReturnListOfUsersForAuthenticatedRequests() {
    when(userService.getAllUsers())
      .thenReturn(List.of(new User(42L, "duke"), new User(24L, "mike")));

    this.webTestClient
      .get()
      .uri("/api/users")
      .exchange()
      .expectStatus().is2xxSuccessful()
      .expectBody().jsonPath("$.size()", Matchers.is(2));
  }

  private ExchangeFilterFunction logRequest() {
    return (clientRequest, next) -> {
      System.out.printf("Request: %s %s %n", clientRequest.method(), clientRequest.url());
      return next.exchange(clientRequest);
    };
  }
}
