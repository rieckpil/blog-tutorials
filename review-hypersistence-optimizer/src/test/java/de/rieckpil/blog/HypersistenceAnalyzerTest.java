package de.rieckpil.blog;

import io.hypersistence.optimizer.HypersistenceOptimizer;
import io.hypersistence.optimizer.core.config.JpaConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class HypersistenceAnalyzerTest {

  @PersistenceUnit
  private EntityManagerFactory entityManagerFactory;

  @Test
  public void testOptimizer() {
    new HypersistenceOptimizer(
      new JpaConfig(entityManagerFactory)
    ).init();
  }

}
