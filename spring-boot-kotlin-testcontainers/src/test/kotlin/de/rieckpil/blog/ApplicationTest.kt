package de.rieckpil.blog

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTest {

  @Autowired
  private lateinit var bookRepository: BookRepository

  @Autowired
  private lateinit var webTestClient: WebTestClient

  companion object {

    @Container
    val container = PostgreSQLContainer<Nothing>("postgres:12").apply {
      withDatabaseName("testdb")
      withUsername("duke")
      withPassword("s3crEt")
    }

    @JvmStatic
    @DynamicPropertySource
    fun properties(registry: DynamicPropertyRegistry) {
      registry.add("spring.datasource.url", container::getJdbcUrl);
      registry.add("spring.datasource.password", container::getPassword);
      registry.add("spring.datasource.username", container::getUsername);
    }
  }

  @Test
  fun shouldReturnAllBooks() {

    this.bookRepository.save(Book(1L, "42", "Java 14"))
    this.bookRepository.save(Book(2L, "84", "Java 7"))

    this.webTestClient.get()
      .uri("/books")
      .accept(MediaType.APPLICATION_JSON)
      .exchange()
      .expectStatus().is2xxSuccessful
      .expectBody().jsonPath("$.length()").isEqualTo(2)
      .jsonPath("$[0].id").isEqualTo(1)
      .jsonPath("$[0].isbn").isEqualTo(42)
      .jsonPath("$[0].title").isEqualTo("Java 14")
  }

}
