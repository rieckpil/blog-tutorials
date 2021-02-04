package de.rieckpil.blog.assertj;

import de.rieckpil.blog.registration.User;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class AssertJTest {

  @Test
  void basicAssertions() {

    // make sure to import org.assertj.core.api.Assertions
    Assertions.assertThat("duke").contains("d");

    Assertions.assertThat("duke".toUpperCase()).isEqualTo("DUKE");

    Assertions.assertThat("dUkE".toLowerCase())
      .isEqualTo("duke")
      .hasSize(4)
      .endsWith("e");

    Assertions
      .assertThatThrownBy(() -> {
        throw new RuntimeException("Can't reach database on port 5432");
      })
      .hasMessageContaining("5432")
      .isInstanceOf(RuntimeException.class)
      .hasNoCause();
  }

  @Test
  void customErrorMessage() {
    Assertions.assertThatThrownBy(() -> {
      boolean boardingComplete = false;

      Assertions
        .assertThat(boardingComplete)
        .withFailMessage("Expecting boarding completed when last passenger enters the plane")
        .isTrue();
    });
  }

  @Test
  void softAssertions() {
    Assertions.assertThatThrownBy(() -> {
      SoftAssertions softAssertions = new SoftAssertions();

      softAssertions.assertThat("duke")
        .hasSize(5)
        .isEqualTo("ekud")
        .startsWith("m");

      softAssertions.assertAll();
    });
  }

  @Test
  void advancedAssertions() {

    Assertions
      .assertThat(List.of("duke", "mike", "alice", "john"))
      .containsAnyOf("mike", "duke")
      .hasSizeBetween(3, 5)
      .hasOnlyElementsOfType(String.class);


    Assertions
      .assertThat(List.of(
        new User("duke", LocalDateTime.now().minusMonths(3)),
        new User("alice", LocalDateTime.MIN)))
      .extracting(User::getCreatedAt)
      .filteredOn(createdAt -> createdAt.isBefore(LocalDateTime.now()))
      .hasSize(2);

  }
}
