package de.rieckpil.blog.junit5;
// tag::JUnitExampleTest[]

import de.rieckpil.blog.registration.User;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

class JUnit5ExampleTest {

  @BeforeEach
  void beforeEachTest() {
    System.out.println("Will run before each test");
  }

  @AfterEach
  void afterEachTest() {
    System.out.println("Will run before after each test");
  }

  @BeforeAll
  static void beforeAllTests() {
    System.out.println("Will run only once before all tests of this class");
  }

  @AfterAll
  static void afterAllTests() {
    System.out.println("Will run only once after all tests of this class");
  }

  @Test
  void basicTestExample() {
    Assertions.assertEquals("DUKE", "duke".toUpperCase());
  }

  @Test
  @Timeout(5)
    // default time unit is seconds
  void shouldThrowException() {
    Assertions.assertThrows(RuntimeException.class, () -> {
      throw new RuntimeException("Error");
    });
  }

  @Test
  @Tag("slow-test")
  void slowTest() throws InterruptedException {
    Thread.sleep(2000);
  }

  @Test
  @Tag("fast-test")
  void fastTest() {
    Assertions.assertEquals("FAST", "fast".toUpperCase());
  }

  @Test
  void assertionExamples() {

    User userOne = new User();
    User userTwo = userOne;

    int[] openInvoiceIds = {42, 13, 7};

    Assertions.assertEquals(42L, 40L + 2L, "Message on failure");
    Assertions.assertNotEquals("duke", "gopher");

    Assertions.assertTrue(4 % 2 == 0, "Message on failure");

    Assertions.assertNotNull(new BigDecimal("42"));

    Assertions.assertThrows(ArithmeticException.class, () -> {
      int result = 4 / 0;
    });

    // checks for equal object references using ==
    Assertions.assertSame(userOne, userTwo);

    Assertions.assertArrayEquals(new int[]{42, 13, 7}, openInvoiceIds);

  }
}
// end::JUnitExampleTest[]
