package de.rieckpil.blog.testng;

import de.rieckpil.blog.review.ReviewValidation;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class ReviewValidationTest {

  private ReviewValidation cut;

  @BeforeSuite
  public void beforeSuite() {
    System.out.println("Before Suite");
  }

  @BeforeTest
  public void beforeTest() {
    System.out.println("Before Test");
  }

  @BeforeClass
  public void beforeClass() {
    System.out.println("Before Class");
  }

  @BeforeMethod
  public void beforeMethod() {
    System.out.println("Before Method");
    this.cut = new ReviewValidation();
  }

  @Test(groups = {"edge-cases"})
  public void shouldRejectLoremIpsumTitle() {
    String reviewTitle = "Nice book lorem Ipsum dolor sit amet";
    boolean result = cut.titleMeetsQualityStandards(reviewTitle);
    Assert.assertFalse(result, "Lorem Ipsum did pass quality standards");
  }

  @Test(groups = {"edge-cases"})
  public void shouldRejectShortTitle() {
    String reviewTitle = "Good book";
    boolean result = cut.titleMeetsQualityStandards(reviewTitle);
    Assert.assertFalse(result, "A short title did pass quality standards");
  }

  @Test(groups = {"happy-path"})
  public void shouldAllowTitlesOfHighQuality() {
    String reviewTitle = "Good book, I can recommend it to everyone";
    boolean result = cut.titleMeetsQualityStandards(reviewTitle);
    Assert.assertTrue(result, "A title of high quality was rejected");
  }

  @Test(groups = {"windows-only"}, enabled = false)
  public void shouldDetectWindows() {
    Assert.assertTrue(System.getProperty("os.name").contains("Windows"));
  }

  @Test(timeOut = 1000L, enabled = false, description = "Demo @Test Attributes")
  public void shouldFail() throws InterruptedException {
    Thread.sleep(1200L);
    Assert.assertTrue("Lorem ipsum".toLowerCase().equals("lorem ipsum"));
  }

  @DataProvider(name = "shortTitles")
  public Object[][] createShortReviewTitles(Method method) {
    System.out.println("Generating test data for method " + method.getName());
    return new Object[][]{
      {"ABCD"},
      {"Bad book"},
      {":("},
    };
  }

  @Test(dataProvider = "shortTitles")
  public void shouldRejectShortTitles(String reviewTitle) {
    boolean result = cut.titleMeetsQualityStandards(reviewTitle);
    Assert.assertFalse(result, "A short title did pass quality standards");
  }
}
