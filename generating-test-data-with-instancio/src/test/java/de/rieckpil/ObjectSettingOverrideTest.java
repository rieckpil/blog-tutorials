package de.rieckpil;

import static org.assertj.core.api.Assertions.assertThat;

import org.instancio.Instancio;
import org.instancio.settings.Keys;
import org.instancio.settings.StringType;
import org.junit.jupiter.api.Test;

class ObjectSettingOverrideTest {

  @Test
  void shouldCreateUserWithObjectLevelSettings() {
    var user = Instancio.of(User.class).withSetting(Keys.STRING_TYPE, StringType.DIGITS).create();

    assertThat(user.bio()).containsOnlyDigits();
  }

  record User(String bio) {}
}
