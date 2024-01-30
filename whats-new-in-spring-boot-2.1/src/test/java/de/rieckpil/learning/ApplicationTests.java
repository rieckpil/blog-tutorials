package de.rieckpil.learning;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
// no @ExtendWith(SpringExtension.class) needed
class ApplicationTests {

  @Test
  @DisplayName("Load the whole Spring context")
  void contextLoads() {}
}
