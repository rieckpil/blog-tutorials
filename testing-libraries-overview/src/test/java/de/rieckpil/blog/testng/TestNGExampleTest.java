package de.rieckpil.blog.testng;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGExampleTest {

  @BeforeClass
  public void setUp() {
    System.out.println("Invoked before this test is instantiated");
  }

  @BeforeTest
  public void beforeTest() {
    System.out.println("Invoked before each test");
  }

  @Test
  public void firstTest() {
    Assert.assertEquals("DUKE", "duke".toUpperCase());
  }
}
