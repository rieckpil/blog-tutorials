package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RecordApplicationEvents
class UserServiceFullContextTest {

  @Autowired
  private ApplicationEvents applicationEvents;

  @Autowired
  private UserService userService;

  @Test
  void userCreationShouldPublishEvent() {

    System.out.println(applicationEvents.toString());

    this.userService.createUser("duke");

    assertEquals(1, applicationEvents
      .stream(UserCreationEvent.class)
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

