package de.rieckpil.blog;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderedExecutionTest {

  @Test
  @Order(2)
  public void testTwo() {
    System.out.println("Executing testTwo");
    assertEquals(42, 40 + 2);
  }

  @Test
  @Order(1)
  public void testOne() {
    System.out.println("Executing testOne");
    assertEquals(42, 40 + 2);
  }

  @Test
  @Order(3)
  public void testThree() {
    System.out.println("Executing testThree");
    assertEquals(42, 40 + 2);
  }
}
