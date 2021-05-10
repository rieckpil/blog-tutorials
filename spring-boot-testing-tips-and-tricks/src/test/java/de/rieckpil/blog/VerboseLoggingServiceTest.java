package de.rieckpil.blog;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(OutputCaptureExtension.class)
class VerboseLoggingServiceTest {

  private VerboseLoggingService cut = new VerboseLoggingService();

  @Test
  @Disabled("To be investigated why it fails")
  void verifySystemOut(CapturedOutput capturedOutput) {
    cut.notify("Hello World!");

    assertLinesMatch(
      List.of("Hello World!", ".*VerboseLoggingService - Hello World!"),
      Arrays.asList(capturedOutput.getOut().split("\n")));

    assertTrue(capturedOutput.getErr().contains("Error"), "System.err did not contain message");
  }
}
