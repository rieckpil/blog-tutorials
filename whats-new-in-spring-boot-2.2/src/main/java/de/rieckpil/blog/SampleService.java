package de.rieckpil.blog;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Lazy(false)
public class SampleService {

    @PostConstruct
    public void init() {
        System.out.println("SampleService is now initialized");
    }

    public String getMessage() {
        return "Hello World";
    }
}
