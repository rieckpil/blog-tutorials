package de.rieckpil.blog.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;

  private String password;

  private long amountOfApiCalls = 0;

  private long maxApiCallsPerMinute = 10;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public long getAmountOfApiCalls() {
    return amountOfApiCalls;
  }

  public void setAmountOfApiCalls(long amountOfApiCalls) {
    this.amountOfApiCalls = amountOfApiCalls;
  }

  public long getMaxApiCallsPerMinute() {
    return maxApiCallsPerMinute;
  }

  public void setMaxApiCallsPerMinute(long maxApiCallsPerMinute) {
    this.maxApiCallsPerMinute = maxApiCallsPerMinute;
  }

  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", username='" + username + '\'' +
      ", password='" + password + '\'' +
      ", amountOfApiCalls=" + amountOfApiCalls +
      ", maxApiCallsPerMinute=" + maxApiCallsPerMinute +
      '}';
  }
}
