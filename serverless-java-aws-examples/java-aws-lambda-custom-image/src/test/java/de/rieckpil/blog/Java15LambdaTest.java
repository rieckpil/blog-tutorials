package de.rieckpil.blog;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class Java15LambdaTest {

  private Java15Lambda cut = new Java15Lambda();

  @Test
  void shouldReturnMultilineString() {
    String result = this.cut.handleRequest(null, null);

    System.out.println(result);

    assertTrue(result.contains("\n"), "Response is not a multiline String");
  }
}
