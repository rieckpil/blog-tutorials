package de.rieckpil.blog.jsonassert;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

public class JSONAssertTest {

  @Test
  void assertionsForJsonObjects() throws JSONException {
    String result = "{name: 'duke', age:42}";

    // instead of
    // String result = "{\"name\": \"duke\", \"age\":\"42\"}";

    assertEquals("{name: 'duke'}", result, false);

    // JUnit Jupiter import
    assertEquals("JUnit Jupiter", "JUnit Jupiter");

    JSONObject object = new JSONObject()
      .put("name", "duke")
      .put("age", 42);

    assertThrows(AssertionError.class, () -> {
      JSONAssert.assertEquals("Missing or wrong id", "{id: 1337}", object, false);
    });
  }

  @Test
  void assertionsForJsonArrays() throws JSONException {

    JSONObject object = new JSONObject()
      .put("name", "duke")
      .put("age", 42)
      .put("tags", new JSONArray().put("VIP").put("Customer"));

    JSONAssert.assertEquals("{name: 'duke', tags: ['VIP', 'Customer']}", object, false);

    String result = "['mike', 'john', 'alice', 'anna']";
    JSONAssert.assertEquals("['alice', 'anna', 'mike', 'john']", result, false);

    assertThrows(AssertionError.class, () -> {
      String failingExample = "['mike', 'john', 'alice', 'anna']";
      JSONAssert.assertEquals("['alice', 'anna', 'john']", failingExample, false);
    });
  }

  @Test
  void strictnessExample() throws JSONException {
    String result = "{\"name\": \"duke\", \"age\":\"42\"}";
    JSONAssert.assertEquals("{\"name\": \"duke\"}", result, false);

    assertThrows(AssertionError.class, () -> {
      JSONAssert.assertEquals("{\"name\": \"duke\"}", result, true);
    });
  }

  @Test
  void strictnessArrayExample() throws JSONException {
    String result = "['mike', 'john', 'alice', 'anna']";
    JSONAssert.assertEquals("['alice', 'anna', 'mike', 'john']", result, false);

    assertThrows(AssertionError.class, () -> {
      JSONAssert.assertEquals("['mike', 'alice', 'john', 'anna']", result, true);
    });
  }

  @Test
  void strictnessFurtherExamples() throws JSONException {
    String result = "{meetingId: 42, people: ['mike', 'john', 'alice']}";

    JSONAssert.assertEquals("{people: ['mike', 'john', 'alice']}",
      result, JSONCompareMode.STRICT_ORDER);

    JSONAssert.assertEquals("{meetingId: 42, people: ['john', 'alice', 'mike']}",
      result, JSONCompareMode.NON_EXTENSIBLE);

    assertThrows(AssertionError.class, () -> {
      JSONAssert.assertEquals("{people: ['mike', 'john', 'alice']}",
        result, JSONCompareMode.STRICT);
      JSONAssert.assertEquals("{people: ['john', 'alice', 'mike']}",
        result, JSONCompareMode.NON_EXTENSIBLE);
    });

    assertThrows(AssertionError.class, () -> {
      JSONAssert.assertEquals("['mike', 'alice', 'john', 'anna']", result, true);
    });
  }
}
