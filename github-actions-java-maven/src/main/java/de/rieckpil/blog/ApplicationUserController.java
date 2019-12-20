package de.rieckpil.blog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class ApplicationUserController {

  private final ApplicationUserRepository applicationUserRepository;

  public ApplicationUserController(ApplicationUserRepository applicationUserRepository) {
    this.applicationUserRepository = applicationUserRepository;
  }

  @GetMapping
  public List<ApplicationUser> getAllUsers() {
    return applicationUserRepository.findAll();
  }
}
