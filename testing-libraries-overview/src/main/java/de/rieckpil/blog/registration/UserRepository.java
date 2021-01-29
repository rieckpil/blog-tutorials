package de.rieckpil.blog.registration;

public interface UserRepository {
  User findByUsername(String username);

  User save(User user);
}
