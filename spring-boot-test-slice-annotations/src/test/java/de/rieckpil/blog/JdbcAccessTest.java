package de.rieckpil.blog;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
public class JdbcAccessTest {

  @Autowired private DataSource dataSource;

  @Autowired private JdbcTemplate jdbcTemplate;

  @Test
  public void shouldReturnBooks() {
    assertNotNull(dataSource);
    assertNotNull(jdbcTemplate);
  }
}
