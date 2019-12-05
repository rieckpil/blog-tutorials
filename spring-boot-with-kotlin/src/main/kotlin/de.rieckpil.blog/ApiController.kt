package de.rieckpil.blog

import com.fasterxml.jackson.databind.node.ArrayNode
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient

@RestController
@RequestMapping("/api")
class ApiController(
  val jsonPlaceHolderWebClient: WebClient,
  val personRepository: PersonRepository
) {

  @GetMapping(value = ["/todos"], produces = [MediaType.APPLICATION_JSON_VALUE])
  fun getAllTodos() = jsonPlaceHolderWebClient
    .get()
    .uri("/todos")
    .retrieve()
    .bodyToMono(ArrayNode::class.java)
    .block()

  @GetMapping(value = ["/persons"], produces = [MediaType.APPLICATION_JSON_VALUE])
  fun getAllPersons(): List<Person> = personRepository.findAll()
}
