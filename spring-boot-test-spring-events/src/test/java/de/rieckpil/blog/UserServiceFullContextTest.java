package de.rieckpil.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;

@SpringBootTest
@RecordApplicationEvents
class UserServiceFullContextTest {

  @Autowired private ApplicationEvents applicationEvents;

  @Autowired private UserService userService;

  @Test
  void userCreationShouldPublishEvent() {

    System.out.println(applicationEvents.toString());

    this.userService.createUser("duke");

    assertEquals(
        1,
        applicationEvents.stream(UserCreationEvent.class)
            .filter(event -> event.getUsername().equals("duke"))
            .count());

    // There are multiple events recorded
    // PrepareInstanceEvent
    // BeforeTestMethodEvent
    // BeforeTestExecutionEvent
    // UserCreationEvent
    applicationEvents.stream().forEach(System.out::println);
  }

  @Test
  void batchUserCreationShouldPublishEvents(@Autowired ApplicationEvents events) {

    System.out.println(applicationEvents.toString());

    List<Long> result = this.userService.createUser(List.of("duke", "mike", "alice"));

    assertEquals(3, result.size());
    assertEquals(3, events.stream(UserCreationEvent.class).count());
  }
}
