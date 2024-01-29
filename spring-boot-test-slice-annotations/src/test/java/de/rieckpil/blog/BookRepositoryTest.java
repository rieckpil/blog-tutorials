package de.rieckpil.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import jakarta.persistence.EntityManager;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class BookRepositoryTest {

  @Autowired private DataSource dataSource;

  @Autowired private EntityManager entityManager;

  @Autowired private BookRepository bookRepository;

  @Test
  public void testCustomNativeQuery() {
    assertEquals(0, bookRepository.findAll().size());

    assertNotNull(dataSource);
    assertNotNull(entityManager);
  }
}
