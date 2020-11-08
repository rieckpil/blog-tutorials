package de.rieckpil.blog;

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
