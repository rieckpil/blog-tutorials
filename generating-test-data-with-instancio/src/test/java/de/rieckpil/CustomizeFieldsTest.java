package de.rieckpil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.instancio.Select.field;

import java.time.LocalDate;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

class CustomizeFieldsTest {

  @Test
  void shouldGenerateUserWithCustomFieldValues() {
    var user =
        Instancio.of(User.class)
            .generate(field(User::bio), gen -> gen.text().loremIpsum().paragraphs(3).words(200))
            .generate(field(User::age), gen -> gen.ints().range(1, 100))
            .generate(
                field(User::dateOfBirth),
                gen -> gen.temporal().localDate().past().min(LocalDate.ofYearDay(1940, 1)))
            .generate(field(User::cardNumber), gen -> gen.finance().creditCard().masterCard())
            .generate(
                field(User::betAgainst), gen -> gen.oneOf("Conor McGregor", "Michael Chandler"))
            .create();

    assertThat(user.bio().split("\\s+")).hasSize(200);
    assertThat(user.age()).isBetween(1, 100);
    assertThat(user.dateOfBirth()).isBefore(LocalDate.now());
    assertThat(user.cardNumber()).startsWith("5").hasSize(16);
    assertThat(user.betAgainst()).isIn("Conor McGregor", "Michael Chandler");
  }

  record User(String bio, int age, LocalDate dateOfBirth, String cardNumber, String betAgainst) {}
}
