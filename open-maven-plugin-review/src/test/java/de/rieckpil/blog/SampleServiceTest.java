package de.rieckpil.blog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SampleServiceTest {

    private SampleService sampleService;

    @BeforeEach
    public void setup() {
        this.sampleService = new SampleService();
    }

    @Test
    public void testModifyInput() {
        String input = "Hello World";
        assertEquals("Hello World - modified by SampleService", sampleService.modifyString(input));

    }

}