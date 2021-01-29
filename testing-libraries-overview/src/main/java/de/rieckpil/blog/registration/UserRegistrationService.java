package de.rieckpil.blog.registration;

import java.time.LocalDateTime;

public class UserRegistrationService {

  private final UserRepository userRepository;

  public UserRegistrationService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User registerUser(String username) {
    User existingUser = userRepository.findByUsername(username);

    if (existingUser != null) {
      return existingUser;
    }

    return userRepository.save(new User(username, LocalDateTime.now()));
  }
}
