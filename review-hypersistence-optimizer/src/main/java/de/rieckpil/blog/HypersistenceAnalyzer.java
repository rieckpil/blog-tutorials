package de.rieckpil.blog;

import io.hypersistence.optimizer.HypersistenceOptimizer;
import io.hypersistence.optimizer.core.config.JpaConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Component
public class HypersistenceAnalyzer implements CommandLineRunner {

  @PersistenceUnit
  private EntityManagerFactory entityManagerFactory;

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Analyzing JPA & Hibernate setup");
    new HypersistenceOptimizer(new JpaConfig(entityManagerFactory)).init();
  }
}
