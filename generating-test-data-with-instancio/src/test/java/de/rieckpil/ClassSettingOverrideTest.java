package de.rieckpil;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
import org.instancio.junit.WithSettings;
import org.instancio.settings.Keys;
import org.instancio.settings.Settings;
import org.instancio.settings.StringType;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(InstancioExtension.class)
class ClassSettingOverrideTest {

  @WithSettings
  private static final Settings settings =
      Settings.create().set(Keys.STRING_TYPE, StringType.UNICODE).set(Keys.COLLECTION_MAX_SIZE, 10);

  @RepeatedTest(value = 100)
  void shouldCreateUserWithClassLevelSettings() {
    var user = Instancio.of(User.class).create();

    assertThat(user.bio()).inUnicode();

    assertThat(user.petPeeves()).hasSizeLessThanOrEqualTo(10);
  }

  record User(String bio, List<String> petPeeves) {}
}
