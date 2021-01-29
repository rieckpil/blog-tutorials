package de.rieckpil.blog.registration;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserRegistrationServiceTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserRegistrationService cut;




}
