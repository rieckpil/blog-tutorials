package de.rieckpil.blog;

import org.springframework.context.ApplicationEvent;

public class UserCreationEvent extends ApplicationEvent {

  private final String username;
  private final Long id;

  public UserCreationEvent(Object source, String username, Long id) {
    super(source);
    this.username = username;
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public Long getId() {
    return id;
  }


  @Override
  public String toString() {
    return "UserCreationEvent{" +
      "username='" + username + '\'' +
      ", id=" + id +
      ", timestamp=" + super.getTimestamp() +
      '}';
  }
}
