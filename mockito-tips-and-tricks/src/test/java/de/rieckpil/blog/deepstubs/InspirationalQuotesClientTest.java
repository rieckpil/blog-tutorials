package de.rieckpil.blog.deepstubs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InspirationalQuotesClientTest {

  @Mock
  private WebClient webClient;

  @InjectMocks
  private InspirationalQuotesClient cut; // class under test

  @Test
  void shouldReturnInMockingHell() {

    WebClient.RequestHeadersUriSpec requestHeadersUriSpec = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
    WebClient.ResponseSpec responseSpec = Mockito.mock(WebClient.ResponseSpec.class);

    when(webClient.get()).thenReturn(requestHeadersUriSpec);
    when(requestHeadersUriSpec.uri("/api/quotes")).thenReturn(requestHeadersUriSpec);
    when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
    when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just("We've escaped hell"));

    String result = cut.fetchRandomQuote();

    assertEquals("We've escaped hell", result);
  }
}
