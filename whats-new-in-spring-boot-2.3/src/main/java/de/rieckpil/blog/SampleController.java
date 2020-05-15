package de.rieckpil.blog;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
public class SampleController {

  @GetMapping("/messages")
  public List<String> getMessages(@RequestParam("size") @Positive @Max(6) Integer size) {
    return List.of("Hello", "World", "Foo", "Bar", "Duke", "Spring")
      .stream()
      .limit(size)
      .collect(Collectors.toList());

  }

  @GetMapping("/users")
  public List<User> getUsers() {
    return List.of(new User("Duke", LocalDate.now(), true),
      new User("Duke", LocalDate.now().minusDays(1), false));
  }

  @GetMapping("/api/customers")
  public Integer api() {
    if (2 % 2 == 0) {
      throw new RuntimeException("Exception happened and customer Duke only has 42 $ left");
    }
    return 40 + 2;
  }
}
