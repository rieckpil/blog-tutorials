package de.rieckpil.blog.xmlunit;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;
import org.xmlunit.assertj3.XmlAssert;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.DOMDifferenceEngine;
import org.xmlunit.diff.DifferenceEngine;
import org.xmlunit.matchers.CompareMatcher;
import org.xmlunit.xpath.JAXPXPathEngine;
import org.xmlunit.xpath.XPathEngine;

import javax.xml.transform.Source;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class XMLUnitTest {

  @Test
  void compareTwoDocumentsJUnitJupiter() {

    Source expected = Input.fromStream(this.getClass().getResourceAsStream("/xml/customers.xml")).build();
    Source actual = Input.fromString("<customers></customers>").build();

    DifferenceEngine diff = new DOMDifferenceEngine();

    diff.addDifferenceListener((comparison, outcome) ->
      Assertions.fail("XML documents are not similar: " + comparison));

    assertThrows(AssertionError.class, () -> {
      diff.compare(expected, actual);
    });
  }

  @Test
  void compareTwoDocumentsHamcrest() {

    Source expected = Input
      .fromStream(this.getClass().getResourceAsStream("/xml/customers.xml"))
      .build();

    Source actual = Input
      .fromString("<customers></customers>")
      .build();

    assertThrows(AssertionError.class, () -> {
      // Hamcrest
      MatcherAssert
        .assertThat(actual, CompareMatcher.isIdenticalTo(expected));
    });

  }

  @Test
  void compareTwoDocumentsAssertJ() {

    Source expected = Input.fromStream(this.getClass().getResourceAsStream("/xml/customers.xml")).build();
    Source actual = Input.fromString("<customers></customers>").build();

    assertThrows(AssertionError.class, () -> {
      // AssertJ
      XmlAssert.assertThat(expected)
        .and(actual)
        .areIdentical();
    });
  }

  @Test
  void xPathTestExampleJUnitJupiter() throws IOException {

    Source responseBody = Input
      .fromString(new String(this.getClass().getResourceAsStream("/xml/customers.xml").readAllBytes()))
      .build();

    XPathEngine xpath = new JAXPXPathEngine();

    Iterable<Node> allCustomers = xpath.selectNodes("//customer", responseBody);

    for (Node node :
      allCustomers) {
      System.out.println(node);
    }

    System.out.println(allCustomers.spliterator().getExactSizeIfKnown());
    System.out.println(xpath.evaluate("/customers/customer[0]/username/text()", responseBody));
    System.out.println(xpath.evaluate("/customers/customer[0]", responseBody));
  }
}
