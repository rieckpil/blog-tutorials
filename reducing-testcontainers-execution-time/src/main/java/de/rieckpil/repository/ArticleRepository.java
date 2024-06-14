package de.rieckpil.repository;

import de.rieckpil.entity.Article;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, UUID> {

  Boolean existsByTitle(final String title);
}
