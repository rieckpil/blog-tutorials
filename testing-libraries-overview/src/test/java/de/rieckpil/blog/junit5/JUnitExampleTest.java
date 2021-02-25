package de.rieckpil.blog.junit5;
// tag::JUnitExampleTest[]
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class JUnitExampleTest {

  @Test
  void shouldThrowException() {
    assertThrows(RuntimeException.class, () -> {
      throw new RuntimeException("Error");
    });
  }
}
// end::JUnitExampleTest[]
