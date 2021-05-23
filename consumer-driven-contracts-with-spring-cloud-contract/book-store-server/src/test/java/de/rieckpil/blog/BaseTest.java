package de.rieckpil.blog;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public abstract class BaseTest {

  @Mock
  private BookService mockedBookService;

  @BeforeEach
  void setup() {
    when(mockedBookService.getBooks())
      .thenReturn(List.of(new Book("Java 11", "Technology", "42")));

    RestAssuredMockMvc.standaloneSetup(new BookController(mockedBookService));
  }
}
