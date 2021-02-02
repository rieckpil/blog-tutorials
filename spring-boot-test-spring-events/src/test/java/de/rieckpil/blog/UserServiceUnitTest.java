package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RecordApplicationEvents
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserService.class)
class UserServiceTest {

  @Autowired
  private ApplicationEvents applicationEvents;

  @Autowired
  private UserService userService;

  @Test
  void userCreationShouldPublishEvent() {

    this.userService.createUser("duke");

    assertEquals(1, applicationEvents
      .stream(UserCreationEvent.class)
      .filter(event -> event.getUsername().equals("duke"))
      .count());

    applicationEvents.stream().forEach(System.out::println);
  }

  @Test
  void batchUserCreationShouldPublishEvents(@Autowired ApplicationEvents events) {
    List<Long> result = this.userService.createUser(List.of("duke", "mike", "alice"));

    assertEquals(3, result.size());
    assertEquals(3, events.stream(UserCreationEvent.class).count());
  }
}
