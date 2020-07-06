package de.rieckpil.blog;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.sql.DataSource;
import java.sql.SQLException;

@Singleton
@Startup
public class DatabaseSetup {

  @Inject
  Pbkdf2PasswordHash pbkdf2PasswordHash;

  @Resource(lookup = "java:comp/DefaultDataSource")
  DataSource dataSource;

  @PostConstruct
  public void initDefaultUser() {

    executeUpdate("INSERT INTO user (id, username, password, amountOfApiCalls, maxApiCallsPerMinute) VALUES " +
      "(1, 'rieckpil', '" + this.pbkdf2PasswordHash.generate("HelloWorld".toCharArray()) + "', 0, 10)");

    executeUpdate("INSERT INTO user (id, username, password, amountOfApiCalls, maxApiCallsPerMinute) VALUES " +
      "(2, 'duke', '" + this.pbkdf2PasswordHash.generate("HelloWorld".toCharArray()) + "', 0, 5)");

    executeUpdate("INSERT INTO user (id, username, password, amountOfApiCalls, maxApiCallsPerMinute) VALUES " +
      "(3, 'john', '" + this.pbkdf2PasswordHash.generate("HelloWorld".toCharArray()) + "', 0, 1)");

    executeUpdate("INSERT INTO user_roles (id, username, role) VALUES (1, 'rieckpil', 'USER')");
    executeUpdate("INSERT INTO user_roles (id, username, role) VALUES (2, 'rieckpil', 'ADMIN')");
    executeUpdate("INSERT INTO user_roles (id, username, role) VALUES (3, 'duke', 'USER')");
    executeUpdate("INSERT INTO user_roles (id, username, role) VALUES (4, 'john', 'USER')");

    System.out.println("Successfully initialized database with default user");
  }

  private void executeUpdate(String query) {
    try {
      this.dataSource.getConnection().createStatement().executeUpdate(query);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException("unable to setup database");
    }
  }
}
