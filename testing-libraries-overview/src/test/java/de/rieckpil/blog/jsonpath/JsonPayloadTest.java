package de.rieckpil.blog.jsonpath;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonPayloadTest {

  private static String jsonPayload;

  static {
    try {
      jsonPayload = new String(JsonPayloadTest.class
        .getResourceAsStream("/json/customers.json")
        .readAllBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  void basicAttributeAccess() {

    String username = JsonPath.parse(jsonPayload).read("$[0].username");
    String city = JsonPath.parse(jsonPayload).read("$[0].address.city");


    List<String> tags = JsonPath.parse(jsonPayload).read("$..tags[0,1,2]");
    List<String> productNames = JsonPath.parse(jsonPayload).read("$[1,2].orders[0]..name");

    assertEquals("duke42", username);
    assertEquals("Berlin", city);

    assertTrue(tags.containsAll(Arrays.asList("EARLY_BIRD", "VIP", "PLATINUM_MEMBER", "DELAYED_PAYMENTS", "BRONZE_MEMBER", "GOLD_MEMBER")));
    assertTrue(productNames.containsAll(Arrays.asList("iPhone 12", "Laptop", "MacBook Pro", "Kindle")));
  }

  @Test
  void computeDataWithFunctions() {

    double min = JsonPath.parse(jsonPayload).read("$..price.min()");
    double max = JsonPath.parse(jsonPayload).read("$..price.max()");
    double avg = JsonPath.parse(jsonPayload).read("$..price.avg()");
    double stddev = JsonPath.parse(jsonPayload).read("$..price.stddev()");

    int orders = JsonPath.parse(jsonPayload).read("$[0].orders.length()");
    double orderedProducts = JsonPath.parse(jsonPayload).read("$..quantity.sum()");

    Set<String> keys = JsonPath.parse("{\"name\":\"duke\", \"age\": 42}").read("$.keys()");

    assertEquals(0.49, min, 0.01);
    assertEquals(2499.99, max, 0.01);
    assertEquals(655.71, avg, 0.01);
    assertEquals(822.15, stddev, 0.01);

    assertEquals(2, orders);
    assertEquals(196.00, orderedProducts, 0.01);
    assertEquals(2, keys.size());
  }

  @Test
  void filterOperators() {

    // Which orders for the first customer have the paymentMethod DEBIT?
    JsonPath.parse(jsonPayload).read("$[0].orders[?(@.paymentMethod == 'DEBIT')]");

    // Which products are expensive?
    JsonPath.parse(jsonPayload).read("$[*].orders[*].products[?(@.price > 1999)]");

    // Which customer has more than 2 tags?
    JsonPath.parse(jsonPayload).read("$[?(@.tags.size() > 2)]");

    // We can also combine the expressions with || and &&
    JsonPath.parse(jsonPayload).read("$[?(@.tags.size() > 2 || @.address.city in ['Berlin', 'Paris'])]");

    // Which orders have more than one product for the customer duke42?
    JsonPath.parse(jsonPayload).read("$[?(@.username == 'duke42')].orders[?(@.products.length() > 1)]");

    // For which customers did we specify the continent as part of the address?
    JsonPath.parse(jsonPayload).read("$[?(@.address.continent)]");

  }
}
