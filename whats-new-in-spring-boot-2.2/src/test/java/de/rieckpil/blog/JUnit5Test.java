package de.rieckpil.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class JUnit5Test {

  @Test
  public void testMe() {
    var sum = 2 + 2;
    assertEquals(4, sum);
  }
}
