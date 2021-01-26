package de.rieckpil.blog;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

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
        .auth().none()
        .param("amount", 42)
      .when()
        .get("/api/books")
      .then()
        .statusCode(200)
        .body("$.size()", Matchers.equalTo(1))
        .body("[0].id", Matchers.equalTo(42))
        .body("[0].isbn", Matchers.equalTo("42"))
        .body("[0].author", Matchers.equalTo("Duke"))
        .body("[0].title", Matchers.equalTo("REST Assured With Spring Boot"));
  }

  @Test
  void shouldAllowBookRetrievalWithoutAuthenticationShort() {

    Mockito.when(bookService.getAllBooks(anyInt())).thenReturn(
      List.of(new Book(42L, "42", "REST Assured With Spring Boot", "Duke")));

    RestAssuredMockMvc
      .when()
        .get("/api/books")
      .then()
        .statusCode(200)
        .body("$.size()", Matchers.equalTo(1))
        .body("[0].id", Matchers.equalTo(42))
        .body("[0].isbn", Matchers.equalTo("42"))
        .body("[0].author", Matchers.equalTo("Duke"))
        .body("[0].title", Matchers.equalTo("REST Assured With Spring Boot"));
  }

  @Test
  void shouldAllowBookCreationForAuthenticatedAdminUsers() {

    Mockito.when(bookService.createNewBook(any(BookRequest.class))).thenReturn(42L);

    RestAssuredMockMvc
      .given()
        .auth().with(SecurityMockMvcRequestPostProcessors.user("duke").roles("ADMIN"))
        .contentType("application/json")
        .body("{\"title\": \"Effective Java\", \"isbn\":\"978-0-13-468599-1 \", \"author\":\"Joshua Bloch\"}")
      .when()
        .post("/api/books")
      .then()
        .statusCode(201)
        .header("Location", Matchers.containsString("/api/books/42"));
  }

  @Test
  @WithMockUser(username = "duke", roles = {"USER", "EDITOR"})
  void shouldBlockBookCreationForNonAdminUsers() {

    RestAssuredMockMvc
      .given()
        .contentType("application/json")
        .body("{\"title\": \"Effective Java\", \"isbn\":\"978-0-13-468599-1 \", \"author\":\"Joshua Bloch\"}")
      .when()
        .post("/api/books")
      .then()
        .statusCode(403);
  }
}
