package de.rieckpil.blog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// without the parallel test execution configuration, the
// execution would be sequential and take longer
public class ParallelTest {

  @BeforeEach
  public void delayExecution() throws InterruptedException {
    Thread.sleep(1000);
  }

  @Test
  public void testOne() {
    assertEquals(42, 40 + 2);
  }

  @Test
  public void testTwo() {
    assertEquals(42, 40 + 2);
  }

  @Test
  public void testThree() {
    assertEquals(42, 40 + 2);
  }

}
