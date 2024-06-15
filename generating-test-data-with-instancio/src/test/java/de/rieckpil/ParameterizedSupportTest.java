package de.rieckpil;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import org.instancio.junit.InstancioExtension;
import org.instancio.junit.InstancioSource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;

@ExtendWith(InstancioExtension.class)
class ParameterizedSupportTest {

  @InstancioSource
  @ParameterizedTest
  void shouldDoXyz(UUID userId, UserCreationRequest request) {
    assertThat(userId).isNotNull();
    assertThat(request).isNotNull().hasNoNullFieldsOrProperties();
  }

  record UserCreationRequest(String emailId, String password) {}
}
