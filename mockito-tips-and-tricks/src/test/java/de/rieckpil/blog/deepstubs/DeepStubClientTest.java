package de.rieckpil.blog.deepstubs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DeepStubClientTest {

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private WebClient webClient;

  @InjectMocks
  private InspirationalQuotesClient cut; // class under test

  @Test
  void shouldReturnQuoteFromRemoteSystem() {

    Mockito
      .when(webClient
        .get()
        .uri("/api/quotes")
        .retrieve()
        .bodyToMono(String.class)
        .block())
      .thenReturn("That's a code smell");

    String result = cut.fetchRandomQuote();

    assertEquals("That's a code smell", result);
  }
}
