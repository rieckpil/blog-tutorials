package de.rieckpil.blog;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<Void> createNewUser(
    @RequestBody String username,
    UriComponentsBuilder uriComponentsBuilder) {

    Long id = this.userService.createUser(username);

    return ResponseEntity
      .created(uriComponentsBuilder.path("/api/users/{id}").buildAndExpand(id).toUri())
      .build();
  }

}
