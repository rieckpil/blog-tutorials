package de.rieckpil.blog

import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.support.GenericMessage
import org.springframework.web.reactive.function.client.WebClient

@WireMockTest
class FunctionConfigurationTest {

  private lateinit var functionConfiguration: FunctionConfiguration

  @BeforeEach
  fun setUp(wireMockRuntimeInfo: WireMockRuntimeInfo) {
    functionConfiguration = FunctionConfiguration(
      WebClient.builder().baseUrl(wireMockRuntimeInfo.httpBaseUrl).build()
    )
  }

  @Test
  fun `should return quote with author on successful API response`() {

    stubFor(get(urlEqualTo("/qod?languages=en"))
      .willReturn(aResponse()
        .withHeader(HttpHeaders.CONTENT_TYPE, "application/json")
        .withBodyFile("successful-quote-response.json")))

    val result = functionConfiguration
      .fetchRandomQuote()(GenericMessage("", MessageHeaders(mapOf("aws-context" to TestLambdaContext()))))

    assertThat(result)
      .contains("Bel Pesce")
  }
}
