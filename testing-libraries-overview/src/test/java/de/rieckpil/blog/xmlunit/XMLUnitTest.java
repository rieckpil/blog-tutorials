package de.rieckpil.blog.xmlunit;

import org.assertj.core.util.Streams;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;
import org.xmlunit.assertj3.XmlAssert;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.DOMDifferenceEngine;
import org.xmlunit.diff.DifferenceEngine;
import org.xmlunit.matchers.CompareMatcher;
import org.xmlunit.matchers.EvaluateXPathMatcher;
import org.xmlunit.matchers.HasXPathMatcher;
import org.xmlunit.validation.Languages;
import org.xmlunit.validation.ValidationProblem;
import org.xmlunit.validation.ValidationResult;
import org.xmlunit.validation.Validator;
import org.xmlunit.xpath.JAXPXPathEngine;
import org.xmlunit.xpath.XPathEngine;

import javax.xml.transform.Source;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
  void xPathTestExample() throws IOException {

    Source responseBody = Input
      .fromString(new String(this.getClass().getResourceAsStream("/xml/customers.xml").readAllBytes()))
      .build();

    XPathEngine xpath = new JAXPXPathEngine();

    Iterable<Node> allCustomers = xpath.selectNodes("//customer", responseBody);
    Iterable<Node> amountOfVIPs = xpath.selectNodes("//customer/tags/tag[text()=\"VIP\"]", responseBody);
    Iterable<Node> amountOfLaptopOrders = xpath.selectNodes("//products/product/name[text()=\"Laptop\"]", responseBody);
    String cityNameLastCustomer = xpath.evaluate("//customer[last()]/address/city/text()", responseBody);

    assertEquals(3, Streams.stream(allCustomers).count());
    assertEquals(1, Streams.stream(amountOfVIPs).count());
    assertTrue(Streams.stream(amountOfLaptopOrders).count() > 0, "Nobody ordered a Laptop");
    assertEquals("São Paulo", cityNameLastCustomer);

    assertThat(allCustomers).hasSize(3);
    assertThat(amountOfVIPs).hasSize(1);
    assertThat(amountOfLaptopOrders).hasSize(1);
    assertThat(cityNameLastCustomer).isEqualTo("São Paulo");

    MatcherAssert.assertThat(responseBody, HasXPathMatcher.hasXPath("//tags"));

    MatcherAssert.assertThat(responseBody,
      EvaluateXPathMatcher.hasXPath("//customer[last()]/address/city/text()", CoreMatchers.is("São Paulo")));

    MatcherAssert.assertThat(responseBody, CoreMatchers.not(HasXPathMatcher.hasXPath("//cars")));
  }

  @Test
  void xmlSchemaValidation() {

    Validator validator = Validator.forLanguage(Languages.W3C_XML_SCHEMA_NS_URI);
    validator.setSchemaSource(Input.fromStream(XMLUnitTest.class.getResourceAsStream("/xml/customers.xsd")).build());

    ValidationResult validationResult = validator
      .validateInstance(Input.fromStream(XMLUnitTest.class.getResourceAsStream("/xml/customers.xml")).build());

    assertTrue(validationResult.isValid(), "XML payload did not match XSD");
  }

  @Test
  void xmlSchemaValidationFailure() {
    Validator validator = Validator.forLanguage(Languages.W3C_XML_SCHEMA_NS_URI);
    validator.setSchemaSource(Input.fromStream(XMLUnitTest.class.getResourceAsStream("/xml/customers.xsd")).build());

    ValidationResult failingResult = validator
      .validateInstance(Input.fromString("<customers><car></car></customers>").build());

    assertFalse(failingResult.isValid(), "XML payload did match XSD");

    for (ValidationProblem problem : failingResult.getProblems()) {
      System.out.println(problem.getMessage());
    }
  }
}
