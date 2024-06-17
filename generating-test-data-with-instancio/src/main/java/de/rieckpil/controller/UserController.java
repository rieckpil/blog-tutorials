package de.rieckpil.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

  @PostMapping
  public ResponseEntity<Void> createUser(@RequestBody UserCreationRequest request) {
    return ResponseEntity.ok().build();
  }

  public record UserCreationRequest(String name, String email, String password) {}
}
