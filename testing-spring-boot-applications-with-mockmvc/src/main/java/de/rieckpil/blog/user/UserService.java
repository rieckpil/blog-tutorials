package de.rieckpil.blog.user;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final List<User> inMemoryUserList = new ArrayList<>();

  @PostConstruct
  public void init() {
    this.inMemoryUserList.add(new User("duke", "duke@spring.io"));
    this.inMemoryUserList.add(new User("mandy", "mandy@spring.io"));
  }

  public List<User> getAllUsers() {
    return inMemoryUserList;
  }

  public User getUserByUsername(String username) {
    return inMemoryUserList.stream()
        .filter(user -> user.getUsername().equals(username))
        .findFirst()
        .orElseThrow(() -> new UserNotFoundException("Can't find this user *sad smiley*"));
  }

  public void storeNewUser(User user) {
    this.inMemoryUserList.add(user);
  }
}
