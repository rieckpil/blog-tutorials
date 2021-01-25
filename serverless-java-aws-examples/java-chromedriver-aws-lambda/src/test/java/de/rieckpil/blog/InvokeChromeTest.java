package de.rieckpil.blog;

import org.junit.jupiter.api.Test;

class InvokeChromeTest {

  @Test
  void testLambdaInvocation() {
    new InvokeChrome().handleRequest("", null);
  }

}
