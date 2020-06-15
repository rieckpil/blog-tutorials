package de.rieckpil.blog;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public abstract class BaseTest {

  @Mock
  private BookService mockedBookService;

  @Before
  public void setup() {
    when(mockedBookService.getBooks()).thenReturn(List.of(new Book("Java 11", "Technology", "42")));
    RestAssuredMockMvc.standaloneSetup(new BookController(mockedBookService));
  }
}
