package de.rieckpil.blog.xmlunit;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.xmlunit.assertj3.XmlAssert;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.DOMDifferenceEngine;
import org.xmlunit.diff.DifferenceEngine;
import org.xmlunit.matchers.CompareMatcher;

import javax.xml.transform.Source;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class XMLUnitTest {

  @Test
  void compareTwoDocumentsJUnit() {

    Source expected = Input.fromStream(this.getClass().getResourceAsStream("/xml/customers.xml")).build();
    Source actual = Input.fromString("<customers><customer></customer></customers>").build();

    DifferenceEngine diff = new DOMDifferenceEngine();

    diff.addDifferenceListener((comparison, outcome) ->
      Assertions.fail("XML documents are not similar: " + comparison));
    diff.compare(expected, actual);
  }

  @Test
  void compareTwoDocumentsHamcrest() {

    Source expected = Input
      .fromStream(this.getClass().getResourceAsStream("/xml/customers.xml"))
      .build();

    Source actual = Input
      .fromString("<customers><customer></customer></customers>")
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
    Source actual = Input.fromString("<customers><customer></customer></customers>").build();

    assertThrows(AssertionError.class, () -> {
      // AssertJ
      XmlAssert.assertThat(expected)
        .and(actual)
        .areIdentical();
    });
  }
}
