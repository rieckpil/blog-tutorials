package de.rieckpil.blog.then;

public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Long storeNewUser(String username) {

    if (username.length() <= 3) {
      throw new IllegalArgumentException("Username is too short");
    }

    User user = new User();
    user.setName(username);

    user = userRepository.save(user);

    return user.getId();
  }
}
