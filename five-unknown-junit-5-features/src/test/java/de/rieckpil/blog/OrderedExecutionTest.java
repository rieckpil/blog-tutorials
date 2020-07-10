package de.rieckpil.blog;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderedExecutionTest {

  @Test
  public void test() {
    assertEquals(4, 2 + 2);
  }
}
