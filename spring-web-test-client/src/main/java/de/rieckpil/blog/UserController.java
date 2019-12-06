package de.rieckpil.blog;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/users", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  public User getUserById(@PathVariable("id") Long id) {
    return userService
      .getUserById(id)
      .orElseThrow(() -> new UserNotFoundException(String.format("User with id [%s] not found", id)));
  }

  @PostMapping
  public ResponseEntity<Void> createNewUser(@RequestBody @Validated User user, UriComponentsBuilder uriComponentsBuilder) {
    User addedUser = this.userService.addNewUser(user)
      .orElseThrow(() -> new UserAlreadyExistsException(
        String.format("User with id [%s] is already present", user.getId())));

    UriComponents uriComponents =
      uriComponentsBuilder.path("/api/users/{id}").buildAndExpand(addedUser.getId());

    return ResponseEntity.created(uriComponents.toUri()).build();
  }
}

