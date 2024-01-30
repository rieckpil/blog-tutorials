package de.rieckpil.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

@ExtendWith(MockitoExtension.class)
class UserServiceUnitTest {

  @Mock private ApplicationEventPublisher applicationEventPublisher;

  @Captor private ArgumentCaptor<UserCreationEvent> eventArgumentCaptor;

  @InjectMocks private UserService userService;

  @Test
  void userCreationShouldPublishEvent() {

    Long result = this.userService.createUser("duke");

    Mockito.verify(applicationEventPublisher).publishEvent(eventArgumentCaptor.capture());

    assertEquals("duke", eventArgumentCaptor.getValue().getUsername());
  }

  @Test
  void batchUserCreationShouldPublishEvents() {
    List<Long> result = this.userService.createUser(List.of("duke", "mike", "alice"));

    Mockito.verify(applicationEventPublisher, Mockito.times(3))
        .publishEvent(any(UserCreationEvent.class));
  }
}
