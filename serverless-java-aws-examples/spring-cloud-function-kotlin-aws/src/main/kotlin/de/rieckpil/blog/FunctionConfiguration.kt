package de.rieckpil.blog

import com.amazonaws.services.lambda.runtime.Context
import com.fasterxml.jackson.databind.JsonNode
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.messaging.Message
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class FunctionConfiguration(
  private val randomQuotesWebClient: WebClient
) {

  @Bean
  fun fetchRandomQuote(): (Message<Any>) -> String {
    return {

      val awsContext = it.headers["aws-context"] as Context
      val logger = awsContext.logger

      logger.log("Going to fetch a random quote")

      val response = randomQuotesWebClient
        .get()
        .uri("/qod?language={language}", "en")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(JsonNode::class.java)
        .block()

      val quote = response?.get("contents")?.get("quotes")?.get(0)?.get("quote")
      val author = response?.get("contents")?.get("quotes")?.get(0)?.get("author")

      "Quote of the day: $quote from $author"
    }
  }
}
