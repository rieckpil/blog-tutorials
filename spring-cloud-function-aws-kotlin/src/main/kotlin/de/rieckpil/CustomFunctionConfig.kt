package de.rieckpil

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message

@Configuration
class CustomFunctionConfig {

  @Bean
  fun test(): (Message<Any>) -> Unit {
    return {
      println("Works!")
    }
  }
}
