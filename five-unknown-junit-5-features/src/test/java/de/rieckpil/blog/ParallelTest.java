package de.rieckpil.blog;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParallelTest {

  @Test
  public void testOne() {
    assertEquals(4, 2 + 2);
  }

  @Test
  public void testTwo() {
    assertEquals(4, 2 + 2);
  }

  @Test
  public void testThree() {
    assertEquals(4, 2 + 2);
  }

}
