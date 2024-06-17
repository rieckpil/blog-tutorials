package de.rieckpil;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.validation.Validation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;
import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
import org.instancio.junit.WithSettings;
import org.instancio.settings.Keys;
import org.instancio.settings.Settings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(InstancioExtension.class)
class BeanValidationTest {

  @WithSettings
  private final Settings settings = Settings.create().set(Keys.BEAN_VALIDATION_ENABLED, true);

  @Test
  void shouldValidateBeanConstrains() {
    var request = Instancio.of(User.class).create();

    var validator = Validation.buildDefaultValidatorFactory().getValidator();
    var violations = validator.validate(request);

    assertThat(violations).isEmpty();
  }

  record User(@Email String emailId, @Past LocalDate dateOfBirth, @Negative Double bankBalance) {}
}
