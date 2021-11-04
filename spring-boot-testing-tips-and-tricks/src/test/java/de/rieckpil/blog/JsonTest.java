package de.rieckpil.blog;

import com.jayway.jsonpath.JsonPath;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonTest {

  @Test
  void jsonAssertExample() throws JSONException {
    String result = "{\"name\": \"duke\", \"age\":\"42\"}";
    JSONAssert.assertEquals("{\"name\": \"duke\"}", result, false);
  }

  @Test
  void jsonPathExample() {
    String result = "{\"age\":\"42\", \"name\": \"duke\", \"tags\":[\"java\", \"jdk\"]}";

    // Using JUnit 5 Assertions
    assertEquals(2, JsonPath.parse(result).read("$.tags.length()", Long.class));
    assertEquals("duke", JsonPath.parse(result).read("$.name", String.class));
  }
}
