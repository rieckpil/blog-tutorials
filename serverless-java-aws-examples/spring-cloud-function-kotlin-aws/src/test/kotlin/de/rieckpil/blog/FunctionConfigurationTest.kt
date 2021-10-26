package de.rieckpil.blog

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.support.GenericMessage
import org.springframework.web.reactive.function.client.WebClient

class FunctionConfigurationTest {

  private lateinit var mockWebServer: MockWebServer
  private lateinit var functionConfiguration: FunctionConfiguration

  @BeforeEach
  fun setUp() {
    mockWebServer = MockWebServer()
    mockWebServer.start()

    functionConfiguration = FunctionConfiguration(
      WebClient.builder().baseUrl(mockWebServer.url("/").toString()).build()
    )
  }

  @Test
  fun `should return quote with author on successful API response`() {

    val mockResponse = MockResponse()
      .addHeader("Content-Type", "application/json")
      .setBody(FunctionConfigurationTest::class.java.getResource("/stubs/successful-quote-response.json").readText())

    mockWebServer.enqueue(mockResponse)

    val result = functionConfiguration
      .fetchRandomQuote()(GenericMessage("", MessageHeaders(mapOf("aws-context" to TestLambdaContext()))))

    assertThat(result)
      .contains("Bel Pesce")
  }
}
