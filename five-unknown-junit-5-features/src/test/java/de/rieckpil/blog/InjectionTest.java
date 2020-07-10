package de.rieckpil.blog;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;

public class InjectionTest {

  @RepeatedTest(5)
  public void testMethodName(TestInfo testInfo, TestReporter testReporter, RepetitionInfo repetitionInfo) {
    System.out.println(testInfo.getTestMethod().get().getName());
    testReporter.publishEntry("secretMessage", "JUnit 5");
    System.out.println(repetitionInfo.getCurrentRepetition() + " from " + repetitionInfo.getTotalRepetitions());
  }
}
