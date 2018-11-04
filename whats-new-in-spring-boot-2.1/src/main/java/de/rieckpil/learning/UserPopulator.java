package de.rieckpil.learning;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class UserPopulator implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

		List.of("Tom", "Mike", "John", "Andrew").stream()
				.forEach(n -> userRepository.save(new User(n, UUID.randomUUID().toString())));

	}

}
