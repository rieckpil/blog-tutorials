package de.rieckpil.blog.jsonassert;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class JSONAssertTest {

  @Test
  void assertionsForJsonObjects() throws JSONException {
    String result = "{'name': 'duke', 'age':42}";

    // instead of
    // String result = "{\"name\": \"duke\", \"age\":\"42\"}";

    JSONAssert.assertEquals("{'name': 'duke'}", result, false);

  }

  @Test
  void assertionsForJsonArrays() {

  }

  @Test
  void strictnessExample() throws JSONException {
    String result = "{\"name\": \"duke\", \"age\":\"42\"}";
    JSONAssert.assertEquals("{\"name\": \"duke\"}", result, false);

    assertThrows(AssertionError.class, () -> {
      JSONAssert.assertEquals("{\"name\": \"duke\"}", result, true);
    });
  }
}
