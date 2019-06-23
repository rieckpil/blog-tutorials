package de.rieckpil.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWebClientDemoApplication implements CommandLineRunner {

    @Autowired
    private SimpleApiClient simpleApiClient;

    public static void main(String[] args) {
        SpringApplication.run(SpringWebClientDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(simpleApiClient.getTodoFromAPI());
        System.out.println(simpleApiClient.postToTodoAPI());
    }
}
