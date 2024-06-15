package de.rieckpil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.instancio.Select.field;

import java.time.LocalDate;
import org.instancio.Instancio;
import org.instancio.Model;
import org.junit.jupiter.api.Test;

class ReusableModelTest {

  private final Model<User> userModel =
      Instancio.of(User.class)
          .generate(field(User::emailId), gen -> gen.text().pattern("#a#a#a@rieckpil.de"))
          .generate(field(User::dateOfBirth), gen -> gen.temporal().localDate().past())
          .toModel();

  @Test
  void whenUserHasMasterCard_thenXyz() {
    var user =
        Instancio.of(userModel)
            .generate(field(User::creditCardNumber), gen -> gen.finance().creditCard().masterCard())
            .create();

    assertThat(user.creditCardNumber()).startsWith("5").hasSize(16);
  }

  @Test
  void whenUserHasVisa_thenXyz() {
    var user =
        Instancio.of(userModel)
            .generate(field(User::creditCardNumber), gen -> gen.finance().creditCard().visa())
            .create();

    assertThat(user.creditCardNumber()).startsWith("4").hasSize(16);
  }

  record User(String emailId, LocalDate dateOfBirth, String creditCardNumber) {}
}
