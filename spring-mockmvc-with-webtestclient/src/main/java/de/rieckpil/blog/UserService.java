package de.rieckpil.blog;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

  private List<User> userList;

  @PostConstruct
  public void init() {
    this.userList = new ArrayList();
    userList.add(new User(1L, "duke", Set.of("java", "jdk", "spring")));
    userList.add(new User(2L, "spring", Set.of("spring", "cloud", "boot")));
    userList.add(new User(3L, "boot", Set.of("boot", "kotlin", "spring")));
  }

  public List<User> getAllUsers() {
    return this.userList;
  }

  public Optional<User> getUserById(Long id) {
    return this.userList.stream()
      .filter(user -> user.getId() == id)
      .findFirst();
  }

  public Optional<User> addNewUser(User user) {
    if (this.getUserById(user.getId()).isPresent()) {
      return Optional.empty();
    }

    this.userList.add(user);
    return Optional.of(user);
  }

  public void deleteUserById(Long id) {
    this.userList.removeIf(user -> user.getId() == id);
  }
}
