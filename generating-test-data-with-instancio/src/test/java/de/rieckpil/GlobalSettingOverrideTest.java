package de.rieckpil;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.instancio.Instancio;
import org.junit.jupiter.api.RepeatedTest;

class GlobalSettingOverrideTest {

  @RepeatedTest(value = 100)
  void valuesAdhereToGlobalSettings() {
    var article = Instancio.create(Article.class);

    assertThat(article.content).isAlphanumeric();
    assertThat(article.content).hasSizeLessThanOrEqualTo(200);
    assertThat(article.tags).hasSizeLessThanOrEqualTo(50);
  }

  record Article(String content, List<String> tags) {}
}
