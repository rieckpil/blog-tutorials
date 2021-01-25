package de.rieckpil.blog;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(BookController.class)
class BookControllerTest {

  @MockBean
  private BookService bookService;

  @Autowired
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    RestAssuredMockMvc.mockMvc(mockMvc);
  }

  @Test
  void shouldAllowBookRetrievalWithoutAuthentication() {

    Mockito.when(bookService.getAllBooks(42)).thenReturn(
      List.of(new Book(42L, "42", "REST Assured With Spring Boot", "Duke")));

    RestAssuredMockMvc
    .given()
      .param("amount", 42)
    .when()
      .get("/api/books")
    .then()
      .statusCode(200)
      .body("$.size()", Matchers.equalTo(1));

  }

}
