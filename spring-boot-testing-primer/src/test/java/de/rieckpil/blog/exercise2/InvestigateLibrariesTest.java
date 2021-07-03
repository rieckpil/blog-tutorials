package de.rieckpil.blog.exercise2;

import javax.xml.transform.Source;
import java.util.List;

import com.jayway.jsonpath.JsonPath;
import de.rieckpil.blog.customer.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.DOMDifferenceEngine;
import org.xmlunit.diff.DifferenceEngine;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InvestigateLibrariesTest {

  @Test
  void helloWorldJUnit5() {
    assertEquals("duke", "DUKE".toLowerCase());
  }

  @Test
  void helloWorldMockito() {
    CustomerRepository mockedRepository = Mockito.mock(CustomerRepository.class);

    Mockito.when(mockedRepository.findAll()).thenReturn(List.of());

    assertEquals(0, mockedRepository.findAll().size());
  }

  @Test
  void helloWorldAssertJ() {
    Assertions
      .assertThat("duke")
      .isEqualTo("DUKE".toLowerCase());
  }

  @Test
  void helloWorldHamcrest() {
    MatcherAssert
      .assertThat("duke", equalTo("DUKE".toLowerCase()));
  }

  @Test
  void helloWorldJsonAssert() throws Exception {
    String result = "{name: 'duke', age: 42}";
    JSONAssert.assertEquals("{name: 'duke'}", result, false);
  }

  @Test
  void helloWorldJsonPath() {
    String result = "{\"age\":\"42\", \"name\": \"duke\", \"tags\":[\"java\", \"jdk\"]}";

    assertEquals(2, JsonPath.parse(result).read("$.tags.length()", Long.class));
    assertEquals("duke", JsonPath.parse(result).read("$.name", String.class));
  }

  @Test
  void helloWorldXmlUnit() {
    Source expected = Input.fromString("<invoices></invoices>").build();
    Source actual = Input.fromString("<customers></customers>").build();

    DifferenceEngine diff = new DOMDifferenceEngine();

    diff.addDifferenceListener((comparison, outcome) ->
      Assertions.fail("XML documents are not similar: " + comparison));

    assertThrows(AssertionError.class, () -> {
      diff.compare(expected, actual);
    });
  }
}
