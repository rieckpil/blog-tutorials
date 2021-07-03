package de.rieckpil.blog.exercise3;

import org.junit.jupiter.api.Test;

/**
 * Run all integration tests with: mvn failsafe:integration-test failsafe:verify
 */
class BasicIT {

  @Test
  void test() {
    System.out.println("... running an integration test with the Maven Failsafe Plugin");
  }
}
