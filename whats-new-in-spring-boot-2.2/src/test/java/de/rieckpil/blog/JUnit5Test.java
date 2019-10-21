package de.rieckpil.blog;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JUnit5Test {

    @Test
    public void testMe() {
        var sum = 2 + 2;
        assertEquals(4, sum);
    }
}
