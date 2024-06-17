package de.rieckpil;

import static org.assertj.core.api.Assertions.assertThat;

import de.rieckpil.entity.Article;
import de.rieckpil.repository.ArticleRepository;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(MySQLConfiguration.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ArticleRepositoryIT {

  @Autowired private ArticleRepository articleRepository;

  @Test
  void shouldSaveArticleInDatabaseSuccessfully() {
    final var title = RandomString.make();
    final var writtenBy = RandomString.make();

    Boolean userExistsWithEmailId = articleRepository.existsByTitle(title);
    assertThat(userExistsWithEmailId).isFalse();

    final var article = new Article();
    article.setTitle(title);
    article.setWrittenBy(writtenBy);

    articleRepository.save(article);

    userExistsWithEmailId = articleRepository.existsByTitle(title);
    assertThat(userExistsWithEmailId).isTrue();
  }
}
