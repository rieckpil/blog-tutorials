package de.rieckpil.blog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class BadPracticeFileReader implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        InputStream in = this.getClass().getResourceAsStream("/message.txt");
        byte[] allBytes = in.readAllBytes();
        System.out.println(new String(allBytes));
    }
}
