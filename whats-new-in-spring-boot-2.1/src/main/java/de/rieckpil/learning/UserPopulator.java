package de.rieckpil.learning;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class UserPopulator implements CommandLineRunner {

  @Autowired UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {

    List.of("Tom", "Mike", "John", "Andrew").stream()
        .forEach(
            n ->
                userRepository.save(
                    new User(
                        ThreadLocalRandom.current().nextLong(1, 99999),
                        n,
                        UUID.randomUUID().toString())));
  }
}
