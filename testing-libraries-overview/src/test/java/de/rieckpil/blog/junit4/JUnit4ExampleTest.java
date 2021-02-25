package de.rieckpil.blog.junit4;

import de.rieckpil.blog.registration.User;
import org.junit.*;
import org.junit.experimental.categories.Category;

import java.math.BigDecimal;

public class JUnit4ExampleTest {

  @Before
  public void beforeTest() {
    System.out.println("Will run before each test");
  }

  @After
  public void afterTest() {
    System.out.println("Will run before after each test");
  }

  @BeforeClass
  public static void beforeTestClass() {
    System.out.println("Will run only once before all tests of this class");
  }

  @AfterClass
  public static void afterTestClass() {
    System.out.println("Will run only once after all tests of this class");
  }

  @Test
  public void basicTestExample() {
    System.out.println("First Test runs");
    Assert.assertEquals("DUKE", "duke".toUpperCase());
  }

  @Test(timeout = 100L)
  public void shouldRunWithin100Milliseconds() throws InterruptedException {
    System.out.println("Second Test runs");
    Thread.sleep(80);
    Assert.assertEquals("DUKE", "duke".toUpperCase());
  }

  @Test(expected = RuntimeException.class)
  public void shouldThrowException() {
    System.out.println("Third Test runs");
    throw new RuntimeException("Error in test");
  }

  @Test
  @Category(DatabaseTests.class)
  public void testDatabaseQuery() {

  }

  @Test
  public void assertionExamples() {

    User userOne = new User();
    User userTwo = userOne;

    int[] openInvoiceIds = {42, 13, 7};

  Assert.assertEquals("Message on failure", 42L, 40L + 2L);
  Assert.assertNotEquals("duke", "gopher");

  Assert.assertTrue("Message on failure", 4 % 2 == 0);

  Assert.assertNotNull(new BigDecimal("42"));

  Assert.assertThrows(ArithmeticException.class, () -> {
    int result = 4 / 0;
  });

  // checks for equal object references using ==
  Assert.assertSame(userOne, userTwo);

  Assert.assertArrayEquals(new int[]{42, 13, 7}, openInvoiceIds);

  }
}
