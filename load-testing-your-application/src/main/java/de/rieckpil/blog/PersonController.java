package de.rieckpil.blog;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@GetMapping
	public Person getRandomPerson() throws InterruptedException {
		long randomLong = ThreadLocalRandom.current().nextLong(1, 1000);
		Thread.sleep(randomLong);
		return personRepository.findById(randomLong).orElse(new Person("dummy"));
	}

}
