package de.rieckpil.blog.then;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceWithMockitoExtensionTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserService cut;

  @Test
  void shouldStoreUserWhenUsernameIsLongerThanThreeCharacters() {
    User savedUser = new User();
    savedUser.setName("duke");
    savedUser.setId(1L);

    when(userRepository.save(any(User.class))).thenReturn(savedUser);

    Long result = cut.storeNewUser("duke");

    assertEquals(1, result);
  }

  @Test
  void shouldStoreUserWhenUsernameIsLongerThanThreeCharactersNew() {
    when(userRepository.save(any(User.class))).then(invocation -> {
      User storedUser = invocation.getArgument(0);
      storedUser.setId(1L);
      return storedUser;
    });

    Long result = cut.storeNewUser("duke");

    assertEquals(1, result);
  }
}
