package de.rieckpil.blog.registration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserRegistrationServiceTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserRegistrationService cut;

  @Test
  void shouldNotReCreateExistingUserWrongStubbing() {
    Mockito.when(userRepository.findByUsername("mike")).thenReturn(new User());

    // Our mock will return null as the stubbing setup doesn't match
    this.cut.registerUser("duke");
  }

  @Test
  void shouldNotReCreateExistingUser() {
    Mockito.when(userRepository.findByUsername("duke")).thenReturn(new User());

    // Or be more generic
    Mockito.when(userRepository.findByUsername(ArgumentMatchers.anyString()))
      .thenReturn(new User());

    User result = this.cut.registerUser("duke");
  }

  @Test
  void shouldPropagateException() {
    Mockito.when(userRepository.findByUsername("devil"))
      .thenThrow(new RuntimeException("DEVIL'S SQL EXCEPTION"));

    assertThrows(RuntimeException.class, () -> cut.registerUser("devil"));
  }

}
