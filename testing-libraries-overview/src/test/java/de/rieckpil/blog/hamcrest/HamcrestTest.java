package de.rieckpil.blog.hamcrest;

import de.rieckpil.blog.customer.Address;
import de.rieckpil.blog.customer.Customer;
import de.rieckpil.blog.customer.Order;
import de.rieckpil.blog.customer.Product;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static de.rieckpil.blog.hamcrest.HasOrderVolumeGreaterThan.hasOrderVolumeGreaterThan;
import static de.rieckpil.blog.hamcrest.IsVIP.isVIP;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HamcrestTest {

  @Test
  void basicAssertions() {

    // or statically import org.hamcrest.MatcherAssert.assertThat;
    MatcherAssert.assertThat("duke", Matchers.containsString("d"));

    MatcherAssert.assertThat("duke".toUpperCase(), Matchers.equalTo("DUKE"));
    MatcherAssert.assertThat("duke".toUpperCase(), Matchers.is(Matchers.equalTo("DUKE")));
    MatcherAssert.assertThat("duke".toUpperCase(), Matchers.is("DUKE"));

    MatcherAssert.assertThat("dUkE".toLowerCase(), Matchers.allOf(
      Matchers.equalTo("duke"),
      Matchers.hasLength(4),
      Matchers.endsWith("e")
    ));
  }

  @Test
  void customErrorMessage() {
    assertThrows(AssertionError.class, () -> {
      boolean boardingComplete = false;

      MatcherAssert
        .assertThat("Expecting boarding completed when last passenger enters the plane",
          boardingComplete, Matchers.is(true)
        );
    });
  }

  @Test
  void advancedAssertions() throws ParserConfigurationException, IOException, SAXException {

    MatcherAssert
      .assertThat(List.of("duke", "mike", "alice", "john"), Matchers.allOf(
        Matchers.containsInAnyOrder("alice", "john", "mike", "duke"),
        Matchers.not(Matchers.emptyIterable())
      ));

    MatcherAssert
      .assertThat(List.of("duke", "mike", "alice", "john"),
        Matchers.everyItem(
          Matchers.anyOf(
            Matchers.hasLength(4),
            Matchers.endsWith("e")
          )));

    MatcherAssert
      .assertThat(
        BigDecimal.valueOf(39.99),
        Matchers.closeTo(BigDecimal.valueOf(39.9985), BigDecimal.valueOf(0.01)));

    MatcherAssert
      .assertThat(39.99, Matchers.closeTo(39.9985, 0.01));

    // XPath example

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document xml = builder.parse(new InputSource(new StringReader("<customers><customer type=\"VIP\"><name>duke</name></customer></customers>")));

    MatcherAssert
      .assertThat(xml, Matchers.allOf(
        Matchers.hasXPath("/customers/customer[1]/name", Matchers.is("duke")),
        Matchers.hasXPath("//*[@type='VIP']", Matchers.is("duke"))
      ));
  }

  @Test
  void customAssertions() {

    Customer customer = createTestCustomer("duke42");

    MatcherAssert.assertThat(customer, isVIP());

    MatcherAssert.assertThat(customer, hasOrderVolumeGreaterThan(BigDecimal.valueOf(999.99)));

    assertThrows(AssertionError.class,
      () -> MatcherAssert.assertThat(customer, hasOrderVolumeGreaterThan(BigDecimal.valueOf(9999.99))));
  }

  private Customer createTestCustomer(String username) {
    Customer customer = new Customer();
    customer.setId(UUID.randomUUID().toString());
    customer.setTags(Set.of("VIP", "PLATINUM_MEMBER", "EARLY_BIRD"));
    customer.setUsername(username);
    customer.setAddress(new Address("Berlin", "Germany", "12347"));
    customer.setOrders(List.of(
      new Order(List.of(
        new Product("MacBook Pro", BigDecimal.valueOf(1499.99), 3L),
        new Product("Kindle Paperwhite", BigDecimal.valueOf(149.00), 10L)
      ), "DEBIT"),
      new Order(List.of(
        new Product("Milk", BigDecimal.valueOf(0.99), 12L),
        new Product("Chocolate", BigDecimal.valueOf(2.99), 42L)
      ), "CREDIT_CARD")));

    return customer;
  }
}
