package de.rieckpil.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootHibernateFlywayBestPracticesApplication implements CommandLineRunner {

	@Autowired
	private BestReviewedBooksRepository bestReviewedBookRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHibernateFlywayBestPracticesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		for (BestReviewedBooks reviewedBook : bestReviewedBookRepository.findAll()) {
			System.out.println(reviewedBook);
		}

	}

}
