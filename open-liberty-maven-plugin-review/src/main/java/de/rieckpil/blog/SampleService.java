package de.rieckpil.blog;

public class SampleService {
    public String modifyString(String input) {
        return input + " - modified by " + SampleService.class.getSimpleName();
    }
}
