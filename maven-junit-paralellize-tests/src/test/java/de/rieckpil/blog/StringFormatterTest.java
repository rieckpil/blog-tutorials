package de.rieckpil.blog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringFormatterTest {

  private StringFormatter cut = new StringFormatter();

  @BeforeEach
  void artificialDelay() throws Exception {
    // delaying the test execution to see the parallelization efforts
    Thread.sleep(1000);
  }

  @Test
  void shouldUppercaseLowercaseString() {
    String input = "duke";

    String result = cut.format(input);

    assertEquals("DUKE", result);
  }

  @Test
  void shouldReturnAlreadyUppercaseString() {
    String input = "MIKE";

    String result = cut.format(input);

    assertEquals("MIKE", result);
  }

  @Test
  void shouldHandleEmptyString() {
    String input = "";

    String result = cut.format(input);

    assertEquals("", result);
  }
}
