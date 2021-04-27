package de.rieckpil.blog.deepstubs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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
        .uri(ArgumentMatchers.anyString())
        .retrieve()
        .bodyToMono(String.class))
      .thenReturn(Mono.just("Less setup hell - but not better"));

    String result = cut.fetchRandomQuote();

    assertEquals("Less setup hell - but not better", result);

    Mockito.verify(webClient.get().uri("/quotes").retrieve()).bodyToMono(String.class);

    // The following verifications won't work and fail
    // Mockito.verify(webClient.get().uri("/quotes")).retrieve();
    // Mockito.verify(webClient.get()).uri("/quotes");
    // Mockito.verify(webClient).get();
  }
}
